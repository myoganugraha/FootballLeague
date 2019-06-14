package io.myoganugraha.footballleague.View.LeagueDetail

import io.myoganugraha.footballleague.Model.League

interface LeagueDetailView {
    fun showLeagueDetail(league: League)
    fun showLoading()
    fun hideLoading()
}