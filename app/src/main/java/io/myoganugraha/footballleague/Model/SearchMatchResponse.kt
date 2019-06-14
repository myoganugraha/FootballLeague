package io.myoganugraha.footballleague.Model

import com.google.gson.annotations.SerializedName

data class SearchMatchResponse(
    @SerializedName("event")
    val event: MutableList<Match>
)