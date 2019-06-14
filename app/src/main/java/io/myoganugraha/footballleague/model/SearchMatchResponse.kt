package io.myoganugraha.footballleague.model

import com.google.gson.annotations.SerializedName

data class SearchMatchResponse(
    @SerializedName("event")
    val event: MutableList<Match>
)