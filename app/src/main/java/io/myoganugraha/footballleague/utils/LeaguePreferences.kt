package io.myoganugraha.footballleague.utils

import android.content.Context

class LeaguePreferences(context: Context) {
    val PREFERENCE_NAME = "LeaguePreference"
    val LEAGUE_ID = "LEAGUE_ID"

    val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getLeagueID(): String {
        return preferences.getString(LEAGUE_ID, "")
    }

    fun saveLeagueID(leagueID: String) {
        val editor = preferences.edit()
        editor.putString(LEAGUE_ID, leagueID)
        editor.apply()
    }
}