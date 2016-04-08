package chapssheet

class Member {

    Integer id
    String name
    String surname


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
