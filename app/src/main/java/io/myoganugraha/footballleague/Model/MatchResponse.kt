package io.myoganugraha.footballleague.Model

import com.google.gson.annotations.SerializedName

data class MatchResponse(
    @SerializedName("events")
    val events: MutableList<Match>
)