package io.myoganugraha.footballleague.view.LeagueDetail

import io.myoganugraha.footballleague.model.League

interface LeagueDetailView {
    fun showLeagueDetail(league: League)
    fun showLoading()
    fun hideLoading()
}