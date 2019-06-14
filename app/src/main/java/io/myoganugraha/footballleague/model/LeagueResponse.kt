package io.myoganugraha.footballleague.model

import com.google.gson.annotations.SerializedName

data class LeagueResponse(@SerializedName("leagues") val leagues: MutableList<League>)