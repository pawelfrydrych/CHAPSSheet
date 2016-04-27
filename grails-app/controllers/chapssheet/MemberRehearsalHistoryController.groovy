package chapssheet

import grails.rest.RestfulController
import grails.transaction.Transactional


@Transactional(readOnly = false)
class MemberRehearsalHistoryController extends RestfulController<MemberRehearsalHistory> {

    static responseFormats = ['json']

    MemberRehearsalHistoryController()
    {
        super(MemberRehearsalHistory)
    }

    MemberRehearsalHistoryController(Class<MemberRehearsalHistory> resource) {
        super(resource)
    }

    def save(MemberRehearsalDTO memberRehearsalDTO)
    {

        if(!memberRehearsalDTO.validate())
        {
            respond memberRehearsalDTO.errors
            return
        }

        def memReh = MemberRehearsalHistory.findByMemberAndRehearsal(memberRehearsalDTO.member, memberRehearsalDTO.rehearsal)
        println memReh



        if(!memReh)
        {
            MemberRehearsalHistory  memberRehearsalHistory = new MemberRehearsalHistory(member: memberRehearsalDTO.member,
                    out: memberRehearsalDTO.out, attendance: memberRehearsalDTO.attendance, delay: memberRehearsalDTO.delay,
                    rehearsal: memberRehearsalDTO.rehearsal)


            def result = memberRehearsalHistory.save()
            if(!result)
            {
                respond memberRehearsalHistory.errors
            }

            respond result

        }else
        {
            memReh.delay = memberRehearsalDTO.delay
            memReh.attendance = memberRehearsalDTO.attendance
            memReh.out = memberRehearsalDTO.out

            def result = memReh.save(flush:true)
            if(!result)
            {
                respond memReh.errors
            }

            respond result
        }

    }

}


class MemberRehearsalDTO
{

    boolean attendance
    Integer delay
    Integer out
    Member member
    Rehearsal rehearsal

    static constraints = {

        attendance nullable: true
        delay nullable: true
        out nullable: true
        member nullable: true
        rehearsal nullable:true

    }


}