package io.myoganugraha.footballleague.View.SearchResult

import io.myoganugraha.footballleague.Model.Match

interface SearchResultView {
    fun showData(data: MutableList<Match>)
    fun showLoading()
    fun hideLoading()
}