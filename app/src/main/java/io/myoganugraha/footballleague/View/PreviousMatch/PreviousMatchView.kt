package io.myoganugraha.footballleague.View.PreviousMatch

import io.myoganugraha.footballleague.Model.Match

interface PreviousMatchView {
    fun showLoading()
    fun hideLoading()
    fun showPreviousMatch(data: MutableList<Match>)
}