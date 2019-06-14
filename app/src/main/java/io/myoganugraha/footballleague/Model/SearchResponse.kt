package io.myoganugraha.footballleague.Model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("event")
    var match: MutableList<Match>
)