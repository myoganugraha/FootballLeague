package io.myoganugraha.footballleague.view.PreviousMatch

import io.myoganugraha.footballleague.model.Match

interface PreviousMatchView {
    fun showLoading()
    fun hideLoading()
    fun showPreviousMatch(data: MutableList<Match>)
}