package io.myoganugraha.footballleague.View.MatchDetail

import io.myoganugraha.footballleague.Model.Match

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetailData(match: Match)
}