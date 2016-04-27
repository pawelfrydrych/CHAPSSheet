package chapssheet

import grails.rest.RestfulController
import grails.transaction.Transactional
import groovy.time.TimeCategory


@Transactional(readOnly = false)
class RehearsalController extends RestfulController<Rehearsal> {

    static responseFormats = ['json']

    RehearsalController()
    {
        super(Rehearsal)
    }


    RehearsalController(Class<Rehearsal> resource) {
        super(resource)
    }

    def show()
    {
        respond Rehearsal.findAll()
    }


    def save(RehearsalDTO rehearsalDTO)
    {
        if(!rehearsalDTO.validate())
        {
            respond rehearsalDTO.errors
            return
        }

        use(TimeCategory)
        {

            def firstDateEnd = rehearsalDTO.dateEnd
            int totalMinutes = (rehearsalDTO.dateEnd - rehearsalDTO.dateStart).hours * 60 + (rehearsalDTO.dateEnd - rehearsalDTO.dateStart  ).minutes;
            int howManyRehearsalInOneRehearsal = rehearsalDTO.breakCount + 1

            int rehearsalLenght = ( totalMinutes - (rehearsalDTO.breakCount * 15) ) / howManyRehearsalInOneRehearsal

            Date dateStart = rehearsalDTO.dateStart;
            Date dateEnd = dateStart + (rehearsalLenght).minutes + 15.minutes

            for(int i = 1; i <= howManyRehearsalInOneRehearsal; i++)
            {
                if(i == howManyRehearsalInOneRehearsal)
                {
                   // dateEnd = dateEnd - 15.minutes;
                    dateEnd = firstDateEnd
                }

                Rehearsal rehearsal = new Rehearsal(dateStart: dateStart, dateEnd:
                        dateEnd, value: rehearsalDTO.value,
                        breakCount: rehearsalDTO.breakCount)

                dateStart = dateEnd
                dateEnd = dateStart + (rehearsalLenght).minutes + 15.minutes


                def result = rehearsal.save()
                if(!result)
                {
                    respond rehearsal.errors
                }

                respond result

            }

        }

    }

}


class RehearsalDTO
{

    Date dateStart
    Date dateEnd
    Integer value
    Integer breakCount

    static constraints = {

        dateStart nullable: false
        dateEnd nullable: false
        value nullable: false
        breakCount nullable: false

    }


}
