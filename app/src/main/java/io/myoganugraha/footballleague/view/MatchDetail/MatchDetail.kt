package io.myoganugraha.footballleague.view.MatchDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.cardview.widget.CardView
import com.squareup.picasso.Picasso
import io.myoganugraha.footballclub.Model.Team
import io.myoganugraha.footballleague.model.Match
import io.myoganugraha.footballleague.network.RetrofitClient
import io.myoganugraha.footballleague.R
import io.myoganugraha.footballleague.utils.DateParser
import kotlinx.android.synthetic.main.activity_match_detail.*

class MatchDetail : AppCompatActivity(), MatchDetailView {

    lateinit var matchID: String
    lateinit var homeTeamID: String
    lateinit var awayTeamID: String
    lateinit var matchDetailPresenter : MatchDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        matchID = intent.getStringExtra("matchID")
        homeTeamID = intent.getStringExtra("homeTeamID")
        awayTeamID = intent.getStringExtra("awayTeamID")

        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        setComponents()
    }

    private fun setComponents() {
        val retrofitClient = RetrofitClient()
        matchDetailPresenter = MatchDetailPresenter(this, retrofitClient)
        matchDetailPresenter.getMatchDetail(matchID)
        matchDetailPresenter.getHomeTeamDetail(homeTeamID)
        matchDetailPresenter.getAwayTeamDetail(awayTeamID)
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

    override fun showHomeTeamData(team: Team) {
        Picasso.get().load(team.teamBadge).into(civ_home_team_detail_match)
    }

    override fun showAwayTeamData(team: Team) {
        Picasso.get().load(team.teamBadge).into(civ_away_team_detail_match)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
