package io.myoganugraha.footballleague.Model

import com.google.gson.annotations.SerializedName

data class LeagueResponse(@SerializedName("leagues") val leagues: MutableList<League>)