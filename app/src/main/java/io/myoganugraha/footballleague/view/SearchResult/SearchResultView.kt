package io.myoganugraha.footballleague.view.SearchResult

import io.myoganugraha.footballleague.model.Match

interface SearchResultView {
    fun showData(data: MutableList<Match>)
    fun showLoading()
    fun hideLoading()
}