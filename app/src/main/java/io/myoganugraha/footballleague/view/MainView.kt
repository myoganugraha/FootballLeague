package io.myoganugraha.footballleague.view

import io.myoganugraha.footballleague.model.LeagueResponse
import io.myoganugraha.footballleague.model.Match

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showLeague(data: LeagueResponse)
    fun showMatch(data: MutableList<Match>)
}