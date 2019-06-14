package io.myoganugraha.footballleague.model

import com.google.gson.annotations.SerializedName

data class MatchResponse(
    @SerializedName("events")
    val events: MutableList<Match>
)