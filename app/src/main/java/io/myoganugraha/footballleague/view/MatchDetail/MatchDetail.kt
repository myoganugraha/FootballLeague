package io.myoganugraha.footballleague.view.MatchDetail

import android.database.sqlite.SQLiteConstraintException
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import io.myoganugraha.footballclub.Model.Team
import io.myoganugraha.footballleague.model.Match
import io.myoganugraha.footballleague.network.RetrofitClient
import io.myoganugraha.footballleague.R
import io.myoganugraha.footballleague.database.NextMatchFavorite
import io.myoganugraha.footballleague.database.PreviousMatchFavorite
import io.myoganugraha.footballleague.database.database
import io.myoganugraha.footballleague.utils.DateParser
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class MatchDetail : AppCompatActivity(), MatchDetailView {

    lateinit var matchID: String
    lateinit var homeTeamID: String
    lateinit var awayTeamID: String
    lateinit var matchDetailPresenter : MatchDetailPresenter

    private var isFavorite : Boolean = false
    private var match: Match? = null
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        matchID = intent.getStringExtra("matchID")
        homeTeamID = intent.getStringExtra("homeTeamID")
        awayTeamID = intent.getStringExtra("awayTeamID")

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = "Match Detail"
        }
        setComponents()
        previousMatchFavoriteState()
        nextMatchFavoriteState()
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

        this.match = match

        matchDetailDate.text = DateParser().parser(match.dateEvent)

        tv_home_team_match_detail.text = match.strHomeTeam
        tv_away_team_match_detail.text = match.strAwayTeam

        tv_score_home_team_match_detail.text = match.intHomeScore
        tv_score_away_team_match_detail.text = match.intAwayScore

        tv_home_team_goals_scored.text = match.strHomeGoalDetails
        tv_away_team_goals_scored.text = match.strAwayGoalDetails

        tv_home_team_shots.text = match.intHomeShots
        tv_away_team_shots.text = match.intAwayShots

        tv_home_team_goalkeeper.text = match.strHomeLineupGoalkeeper
        tv_away_team_goalkeeper.text = match.strAwayLineupGoalkeeper

        tv_home_team_defense.text = match.strHomeLineupDefense
        tv_away_team_defense.text = match.strAwayLineupDefense

        tv_home_team_midfield.text = match.strHomeLineupMidfield
        tv_away_team_midfield.text = match.strAwayLineupMidfield

        tv_home_team_forward.text = match.strHomeLineupForward
        tv_away_team_forward.text = match.strAwayLineupForward

        tv_home_team_sub.text = match.strHomeLineupSubstitutes
        tv_away_team_sub.text = match.strAwayLineupSubstitutes

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
            tv_home_team_formation.text = "-"
        } else {
            tv_home_team_formation.text = match.strHomeFormation
        }
        if (match.strAwayFormation == "") {
            tv_away_team_formation.text = "-"
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.match_detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite()
                else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    private fun previousMatchFavoriteState() {
        database.use {
            val result = select(PreviousMatchFavorite.PREVIOUS_MATCH_FAVORITE_TB).whereArgs("(ID_EVENT = {id})", "id" to matchID)
            val favorite = result.parseList(classParser<PreviousMatchFavorite>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    private fun nextMatchFavoriteState() {
        database.use {
            val result = select(NextMatchFavorite.NEXT_MATCH_FAVORITE_TB).whereArgs("(ID_EVENT = {id})", "id" to matchID)
            val favorite = result.parseList(classParser<NextMatchFavorite>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite() {
        if (match?.idEvent == null) {
            Toast.makeText(this, "Please wait until data loaded", Toast.LENGTH_SHORT).show()
        } else {
            if (match?.intHomeScore != null) {
                try {
                    database.use {
                        insert(
                            PreviousMatchFavorite.PREVIOUS_MATCH_FAVORITE_TB,
                            PreviousMatchFavorite.ID_EVENT to match?.idEvent,
                            PreviousMatchFavorite.ID_HOME_TEAM to match?.idHomeTeam,
                            PreviousMatchFavorite.ID_AWAY_TEAM to match?.idAwayTeam,
                            PreviousMatchFavorite.DATE_EVENT to match?.dateEvent,
                            PreviousMatchFavorite.HOME_TEAM_NAME to match?.strHomeTeam,
                            PreviousMatchFavorite.AWAY_TEAM_NAME to match?.strAwayTeam,
                            PreviousMatchFavorite.HOME_TEAM_SCORE to match?.intHomeScore,
                            PreviousMatchFavorite.AWAY_TEAM_SCORE to match?.intAwayScore
                        )
                    }
                    Toast.makeText(this, "Previous match added to Favorite", Toast.LENGTH_SHORT).show()
                } catch (e: SQLiteConstraintException) {
                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }
            } else {
                try {
                    database.use {
                        insert(
                            NextMatchFavorite.NEXT_MATCH_FAVORITE_TB,
                            NextMatchFavorite.ID_EVENT to match?.idEvent,
                            NextMatchFavorite.ID_HOME_TEAM to match?.idHomeTeam,
                            NextMatchFavorite.ID_AWAY_TEAM to match?.idAwayTeam,
                            NextMatchFavorite.DATE_EVENT to match?.dateEvent,
                            NextMatchFavorite.HOME_TEAM_NAME to match?.strHomeTeam,
                            NextMatchFavorite.AWAY_TEAM_NAME to match?.strAwayTeam,
                            NextMatchFavorite.HOME_TEAM_SCORE to match?.intHomeScore,
                            NextMatchFavorite.AWAY_TEAM_SCORE to match?.intAwayScore
                        )
                    }
                    Toast.makeText(this, "Next match added to Favorite", Toast.LENGTH_SHORT).show()
                } catch (e: SQLiteConstraintException) {
                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun removeFromFavorite() {
        if (match?.intHomeScore != null) {
            try {
                database.use {
                    delete(PreviousMatchFavorite.PREVIOUS_MATCH_FAVORITE_TB, "(ID_EVENT =  {id})", "id" to matchID)
                }
                Toast.makeText(this, "Selected previous match removed from Favorite", Toast.LENGTH_SHORT).show()
            } catch (e: SQLiteConstraintException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        } else {
            try {
                database.use {
                    delete(NextMatchFavorite.NEXT_MATCH_FAVORITE_TB, "(ID_EVENT =  {id})", "id" to matchID)
                }
                Toast.makeText(this, "Selected next match removed from Favorite", Toast.LENGTH_SHORT).show()
            } catch (e: SQLiteConstraintException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp)
        }
    }

}
