package io.myoganugraha.footballleague.database

data class PreviousMatchFavorite(
    val id: Int?,
    val idEvent: String?,
    val idHomeTeam: String?,
    val idAwayTeam: String?,
    val dateEvent: String?,
    val strHomeTeam: String?,
    val strAwayTeam: String?,
    val intHomeScore: String?,
    val intAwayScore: String?
) {
    companion object {
        const val PREVIOUS_MATCH_FAVORITE_TB : String = "PREVIOUS_MATCH_FAVORITE_TB"
        const val ID : String = "ID"
        const val ID_EVENT : String = "ID_EVENT"
        const val ID_HOME_TEAM : String = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM : String = "ID_AWAY_TEAM"
        const val DATE_EVENT : String = "DATE_EVENT"
        const val HOME_TEAM_NAME : String = "HOME_TEAM_NAME"
        const val AWAY_TEAM_NAME : String = " AWAY_TEAM_NAME"
        const val HOME_TEAM_SCORE : String = "HOME_TEAM_SCORE"
        const val AWAY_TEAM_SCORE : String = "AWAY_TEAM_SCORE"
    }
}