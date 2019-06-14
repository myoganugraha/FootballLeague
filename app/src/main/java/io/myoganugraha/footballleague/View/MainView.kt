package io.myoganugraha.footballleague.View

import io.myoganugraha.footballleague.Model.League
import io.myoganugraha.footballleague.Model.LeagueResponse
import io.myoganugraha.footballleague.Model.Match

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showLeague(data: LeagueResponse)
    fun showMatch(data: MutableList<Match>)
}