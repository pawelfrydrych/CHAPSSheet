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


    def save(RehearsalDTO rehearsalDTO)
    {
        if(!rehearsalDTO.validate())
        {
            respond rehearsalDTO.errors
            return
        }

        use(TimeCategory)
        {

            int totalMinutes = (rehearsalDTO.dateEnd - rehearsalDTO.dateStart).hours * 60 + (rehearsalDTO.dateEnd - rehearsalDTO.dateStart  ).minutes;
            int howManyRehearsalInOneRehearsal = rehearsalDTO.breakCount + 1
            Rehearsal[] rehearsals = new Rehearsal[howManyRehearsalInOneRehearsal]

            int rehearsalLenght = ( totalMinutes - (rehearsalDTO.breakCount * 15) ) / howManyRehearsalInOneRehearsal

            Date dateStart = rehearsalDTO.dateStart;
            Date dateEnd = dateStart + (rehearsalLenght).minutes + 15.minutes


            println('totalminutes ' + totalMinutes)
            println ('howManyReh' + howManyRehearsalInOneRehearsal)
            println ('rehearsalLeng' + rehearsalLenght)
            println ('dateStar' + dateStart)

            for(int i = 1; i <= howManyRehearsalInOneRehearsal; i++)
            {
                if(i == howManyRehearsalInOneRehearsal)
                {
                    dateEnd = dateEnd - 15.minutes;
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
