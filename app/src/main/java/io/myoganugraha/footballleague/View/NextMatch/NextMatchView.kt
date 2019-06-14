package io.myoganugraha.footballleague.View.NextMatch

import io.myoganugraha.footballleague.Model.Match

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showNextMatch(data: MutableList<Match>)
}