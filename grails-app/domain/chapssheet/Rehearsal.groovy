package chapssheet

class Rehearsal {

    /**
     * Data rozpoczęcia próby
     */
    Date dateStart

    /**
     * Data zakończenia próby
     */
    Date dateEnd

    /**
     * Waga // Koncert - 2, Próba - 1
     */
    Integer value

    Integer breakCount

    static hasMany = [members: Member]

    static mapping = {
        table 'rehearsals'
        id column: 'id'
        dateStart column: 'date_start'
        dateEnd column: 'date_end'
        value column: 'value'
        members joinTable: [name: 'member_rehearsal', key: 'rehearsal_id', column: 'member_id']
    }

    static constraints = {
        dateStart nullable: false
        dateEnd nullable: false
        value nullable: false
        breakCount nullable: true
    }

}
