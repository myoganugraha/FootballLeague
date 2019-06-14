package io.myoganugraha.footballleague.view.Main

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.myoganugraha.footballleague.adapter.LeagueAdapter
import io.myoganugraha.footballleague.model.LeagueModel
import io.myoganugraha.footballleague.R
import io.myoganugraha.footballleague.utils.LeaguePreferences
import io.myoganugraha.footballleague.view.LeagueDetail.LeagueDetail
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {

    private var leagueItem: MutableList<LeagueModel> = mutableListOf()
    private lateinit var leaguePreferences: LeaguePreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        MainActivityUI().setContentView(this)
        leaguePreferences = LeaguePreferences(this)
        showRV()

    }

    class MainActivityUI : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout {
                lparams(matchParent, matchParent)
                orientation = LinearLayout.VERTICAL

                recyclerView {
                    id = R.id.rvLeague
                    lparams(matchParent, wrapContent)
                }
            }
        }
    }

    class LeagueItem : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            linearLayout {
                orientation = LinearLayout.VERTICAL
                lparams(matchParent, wrapContent)
                padding = dip(12)

                imageView {
                    id = R.id.id_league_badge
                    imageResource = R.drawable.english_premier_league
                    this@linearLayout.gravity = Gravity.CENTER_HORIZONTAL
                }.lparams(dip(100), dip(100))

                textView {
                    id = R.id.id_league_name
                    text = resources.getString(R.string.app_name)
                    gravity = Gravity.CENTER_HORIZONTAL
                }.lparams(matchParent, wrapContent) {
                    margin = dip(10)
                    gravity = Gravity.CENTER_VERTICAL
                }
            }
        }
    }

    private fun initData() {
        val leagueName = resources.getStringArray(R.array.league_name)
        val leagueImage = resources.obtainTypedArray(R.array.league_image)
        val leagueDescription = resources.getStringArray(R.array.league_desc)
        val leagueID = resources.getStringArray(R.array.league_id)
        leagueItem.clear()
        for (i in leagueName.indices) {
            leagueItem.add(LeagueModel(leagueID[i], leagueName[i], leagueImage.getResourceId(i, 0), leagueDescription[i]))
        }
        leagueImage.recycle()
    }

    private fun showRV() {
        findViewById<RecyclerView>(R.id.rvLeague).layoutManager = GridLayoutManager(this, 2)
        findViewById<RecyclerView>(R.id.rvLeague).adapter = LeagueAdapter(leagueItem, {leagueModel: LeagueModel ->  onItemClicked(leagueModel)})
    }

    private fun onItemClicked(league: LeagueModel) {
        val intent = Intent(this, LeagueDetail::class.java)
        intent.putExtra("leagueID", league.leagueID)
        intent.putExtra("leagueName", league.leagueName)
        leaguePreferences.saveLeagueID(league.leagueID)
        startActivity(intent)
    }
}