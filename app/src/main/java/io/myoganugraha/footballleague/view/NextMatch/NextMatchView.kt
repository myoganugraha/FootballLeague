package io.myoganugraha.footballleague.view.NextMatch

import io.myoganugraha.footballleague.model.Match

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showNextMatch(data: MutableList<Match>)
}