package io.myoganugraha.footballclub.Model

import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("teams")
    val teams: MutableList<Team>)