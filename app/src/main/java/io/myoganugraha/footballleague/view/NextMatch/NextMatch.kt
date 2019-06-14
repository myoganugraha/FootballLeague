package io.myoganugraha.footballleague.view.NextMatch


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.myoganugraha.footballleague.adapter.MatchAdapter
import io.myoganugraha.footballleague.model.Match
import io.myoganugraha.footballleague.network.RetrofitClient

import io.myoganugraha.footballleague.R
import io.myoganugraha.footballleague.utils.LeaguePreferences
import io.myoganugraha.footballleague.view.MatchDetail.MatchDetail

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class NextMatch : Fragment(), NextMatchView {

    private lateinit var rvNextsMatch: RecyclerView
    private lateinit var nexteMatchPresenter: NextMatchPresenter
    private lateinit var nextMatchAdapter: MatchAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var leaguePreferences: LeaguePreferences
    private var leagueID: String = ""
    private var match: MutableList<Match> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_next_match, container, false)

        rvNextsMatch = root.findViewById(R.id.rv_nextMatch)
        progressBar = root.findViewById(R.id.progressBarNextMatch)
        leaguePreferences = LeaguePreferences(this.activity!!)

        leagueID = leaguePreferences.getLeagueID()

        initData()
        return root
    }

    private fun initData() {
        val retrofitClient = RetrofitClient()
        nexteMatchPresenter = NextMatchPresenter(this.activity!!, this, retrofitClient)
        nexteMatchPresenter.getNextMatch(leagueID)
    }

    override fun showLoading() {
        progressBar!!.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar!!.visibility = View.GONE
    }

    override fun showNextMatch(data: MutableList<Match>) {
        match.clear()
        match.addAll(data)

        nextMatchAdapter = MatchAdapter(match, {match: Match ->  onItemClicked(match)})
        rvNextsMatch!!.adapter = nextMatchAdapter
        rvNextsMatch!!.setHasFixedSize(true)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)

        rvNextsMatch!!.layoutManager = layoutManager
        nextMatchAdapter.notifyDataSetChanged()
    }

    private fun onItemClicked(match: Match) {
        val intent = Intent(this.activity, MatchDetail::class.java)
        intent.putExtra("matchID", match.idEvent)
        intent.putExtra("homeTeamID", match.idHomeTeam)
        intent.putExtra("awayTeamID", match.idAwayTeam)
        startActivity(intent)
    }


}
