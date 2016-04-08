package chapssheet

class Member {

    Integer id
    String name
    String surname


    /**
     * Przydział głosowy
     */
    Voice voice

    /**
     * Obecność na konkretnej próbie
     */
//    boolean attendance

    /**
     * Wartość liczbowa wyrażana w minutach dot. spóźnienia
     */
 //   Integer delay

    /**
     * Wartość liczbowa wyrażana w minutach dot. opuszczenia próby przed końcem
     */
    //Integer out

    static hasMany = [rehearsal: Rehearsal]
    static belongsTo = Rehearsal

    static mapping = {
        table 'members'
        id column: 'id'
        name column: 'name'
        surname column: 'surname'
        voice column: 'voice'
        rehearsal joinTable: [name: 'member_rehearsal', key: 'member_id', column: 'rehearsal_id']
    }


    static constraints = {
        name blank: false
        surname blank: false

    }

}
