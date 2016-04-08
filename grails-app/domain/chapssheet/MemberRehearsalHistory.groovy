package chapssheet

class MemberRehearsalHistory {

    /**
     * Obecność na konkretnej próbie
     */
    boolean attendance

    /**
     * Wartość liczbowa wyrażana w minutach dot. spóźnienia
     */

    Integer delay

    /**
     * Wartość liczbowa wyrażana w minutach dot. opuszczenia próby przed końcem
     */
    Integer out

    Member member

    Rehearsal rehearsal

    static mapping = {
        table 'member_rehearsal'
        attendance column: 'attendance'
        delay column: 'delay'
        out column: 'out'
        member column: 'member_id'
        rehearsal column: 'rehearsal_id'

    }

    static constraints = {
    }
}
