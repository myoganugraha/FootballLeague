package io.myoganugraha.footballleague.View.SearchResult

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.myoganugraha.footballleague.Adapter.MatchAdapter
import io.myoganugraha.footballleague.Model.Match
import io.myoganugraha.footballleague.Network.RetrofitClient
import io.myoganugraha.footballleague.R
import io.myoganugraha.footballleague.View.MatchDetail.MatchDetail
import kotlinx.android.synthetic.main.activity_search_result.*

class SearchResult : AppCompatActivity(), SearchResultView  {



    private lateinit var mContext : Context
    private lateinit var searchResultPresenter: SearchResultPresenter
    private lateinit var rvSearchResultView: RecyclerView
    private lateinit var matchAdapter: MatchAdapter
    private var match: MutableList<Match> = mutableListOf()
    private var query = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        mContext = this

        query = intent.getStringExtra("query")

        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.title = query

        initComponents()
    }

    private fun initComponents() {

        rvSearchResultView = findViewById(R.id.searcResult_recycler_view)

        initData()
    }

    private fun initData() {
        val retrofitClient = RetrofitClient()
        searchResultPresenter = SearchResultPresenter(mContext, this, retrofitClient)
        searchResultPresenter.searchMatch(query)
    }

    override fun showLoading() {
        findViewById<ProgressBar>(R.id.progressBarSearchResult).visibility = View.VISIBLE
    }

    override fun hideLoading() {
        findViewById<ProgressBar>(R.id.progressBarSearchResult).visibility = View.GONE
    }

    override fun showData(data: MutableList<Match>) {
        match.clear()
        match.addAll(data)

        searchResult.text = "About ${data.size} results"

        matchAdapter = MatchAdapter(match, {match: Match ->  onItemClicked(match)})
        rvSearchResultView!!.adapter = matchAdapter
        rvSearchResultView!!.setHasFixedSize(true)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(mContext)

        rvSearchResultView!!.layoutManager = layoutManager
        matchAdapter.notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun onItemClicked(match: Match) {
        val intent = Intent(this, MatchDetail::class.java)
        intent.putExtra("matchID", match.idEvent)
        startActivity(intent)
    }

}
