package io.myoganugraha.footballleague.View.MatchDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import io.myoganugraha.footballleague.Model.Match
import io.myoganugraha.footballleague.Network.RetrofitClient
import io.myoganugraha.footballleague.R
import io.myoganugraha.footballleague.Utils.DateParser
import kotlinx.android.synthetic.main.activity_match_detail.*
import java.text.ParseException
import java.text.SimpleDateFormat

class MatchDetail : AppCompatActivity(), MatchDetailView {

    lateinit var matchID: String
    lateinit var matchDetailPresenter : MatchDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        matchID = intent.getStringExtra("matchID")

        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        setComponents()
    }

    private fun setComponents() {
        val retrofitClient = RetrofitClient()
        matchDetailPresenter = MatchDetailPresenter(this, retrofitClient)
        matchDetailPresenter.getMatchDetail(matchID)
        Log.d("matchid", matchID)
    }

    override fun showLoading() {
        findViewById<ProgressBar>(R.id.progressBarMatchDetail).visibility = View.VISIBLE
        findViewById<CardView>(R.id.cardview_match_detail).visibility = View.GONE
    }

    override fun hideLoading() {
        findViewById<ProgressBar>(R.id.progressBarMatchDetail).visibility = View.GONE
        findViewById<CardView>(R.id.cardview_match_detail).visibility = View.VISIBLE
    }

    override fun showMatchDetailData(match: Match) {

        matchDetailDate.text = DateParser().parser(match.dateEvent)

        tv_home_team_match_detail.text = match.strHomeTeam
        tv_away_team_match_detail.text = match.strAwayTeam

        tv_score_home_team_match_detail.text = match.intHomeScore
        tv_score_away_team_match_detail.text = match.intAwayScore

        tv_home_team_goals_scored.text = match.strHomeGoalDetails
        tv_away_team_goals_scored.text = match.strAwayGoalDetails

        tv_home_team_shots.text = match.intHomeShots
        tv_away_team_shots.text = match.intAwayShots

        if (match.strHomeYellowCards == "") {
            tv_home_team_yellow_cards.text = "0"
        } else {
            tv_home_team_yellow_cards.text = match.strHomeYellowCards
        }

        if (match.strAwayYellowCards == "") {
            tv_away_team_yellow_cards.text = "0"
        } else {
            tv_away_team_yellow_cards.text = match.strAwayYellowCards
        }

        if (match.strHomeRedCards == "") {
            tv_home_team_red_cards.text = "0"
        } else {
            tv_home_team_red_cards.text = match.strHomeRedCards
        }

        if (match.strAwayRedCards == "") {
            tv_away_team_red_cards.text = "0"
        } else {
            tv_away_team_red_cards.text = match.strAwayRedCards
        }

        if (match.strHomeFormation == "") {
            tv_home_team_formation.text = "0"
        } else {
            tv_home_team_formation.text = match.strHomeFormation
        }
        if (match.strAwayFormation == "") {
            tv_away_team_formation.text = "0"
        } else {
            tv_away_team_formation.text = match.strAwayFormation
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
