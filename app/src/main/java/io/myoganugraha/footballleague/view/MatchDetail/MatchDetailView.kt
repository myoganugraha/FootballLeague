package io.myoganugraha.footballleague.view.MatchDetail

import io.myoganugraha.footballclub.Model.Team
import io.myoganugraha.footballleague.model.Match

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetailData(match: Match)
    fun showHomeTeamData(team: Team)
    fun showAwayTeamData(team: Team)
}