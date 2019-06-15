package io.myoganugraha.footballleague.view.LeagueDetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import io.myoganugraha.footballleague.adapter.PagerAdapter
import io.myoganugraha.footballleague.model.League
import io.myoganugraha.footballleague.network.RetrofitClient
import io.myoganugraha.footballleague.R
import io.myoganugraha.footballleague.view.SearchResult.SearchResult
import kotlinx.android.synthetic.main.activity_league_detail.*
import org.jetbrains.anko.startActivity

class LeagueDetail : AppCompatActivity(), LeagueDetailView {


    private lateinit var leagueEmblem: CircleImageView
    private lateinit var leagueName: TextView
    private lateinit var leagueDetailPresenter: LeagueDetailPresenter
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    lateinit var leagueID: String
    lateinit var leagueNameFromMain: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)

        leagueID = intent.getStringExtra("leagueID")
        leagueNameFromMain = intent.getStringExtra("leagueName")

        setSupportActionBar(toolbarLeagueDetail)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = leagueNameFromMain
        }

        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                startActivity<SearchResult>("query" to query )
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        setComponents()
    }

    private fun setComponents() {
        leagueEmblem = findViewById(R.id.civ_league)
        leagueName = findViewById(R.id.tv_league_name)
        viewPager = findViewById(R.id.viewpagerLeagueDetail)
        tabLayout = findViewById(R.id.tabLayout)

        val retrofitClient = RetrofitClient()
        leagueDetailPresenter = LeagueDetailPresenter(this, retrofitClient)
        leagueDetailPresenter.getLeagueDetail(leagueID)

        viewPager.adapter = PagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun showLeagueDetail(league: League) {
        Picasso.get().load(league.strBadge).into(leagueEmblem)
        leagueName.text = league.strLeague
    }

    override fun showLoading() {
        findViewById<ProgressBar>(R.id.progressBarLeagueDetail).visibility = View.VISIBLE
    }

    override fun hideLoading() {
        findViewById<ProgressBar>(R.id.progressBarLeagueDetail).visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean{
        menuInflater.inflate(R.menu.action_menu, menu)

        val item = menu.findItem(R.id.action_search)
        searchView.setMenuItem(item)

        return true
    }
}
