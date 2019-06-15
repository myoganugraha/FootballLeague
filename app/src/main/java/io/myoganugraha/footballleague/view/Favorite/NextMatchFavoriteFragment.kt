package io.myoganugraha.footballleague.view.Favorite


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import io.myoganugraha.footballleague.R
import io.myoganugraha.footballleague.adapter.NextMatchFavoriteAdapter
import io.myoganugraha.footballleague.database.NextMatchFavorite
import io.myoganugraha.footballleague.database.database
import io.myoganugraha.footballleague.view.MatchDetail.MatchDetail
import kotlinx.android.synthetic.main.fragment_next_match_favorite.view.*
import kotlinx.android.synthetic.main.fragment_previous_match_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFavoriteFragment : Fragment() {

    private lateinit var rvNextMatchFavoriteFragment: RecyclerView
    private lateinit var nextMatchFavoriteAdapter: NextMatchFavoriteAdapter

    private var favoriteMatch: MutableList<NextMatchFavorite> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_next_match_favorite, container, false)
        rvNextMatchFavoriteFragment = view.rv_nextMatchFavorite
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        nextMatchFavoriteAdapter = NextMatchFavoriteAdapter(this.activity!!, favoriteMatch, { match : NextMatchFavorite -> onItemClicked(match)})
        rvNextMatchFavoriteFragment.adapter = nextMatchFavoriteAdapter
        rvNextMatchFavoriteFragment.setHasFixedSize(true)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)
        rvNextMatchFavoriteFragment.layoutManager = layoutManager
    }

    private fun onItemClicked(match: NextMatchFavorite) {
        val intent = Intent(this.activity, MatchDetail::class.java)
        intent.putExtra("matchID", match.idEvent)
        intent.putExtra("homeTeamID", match.idHomeTeam)
        intent.putExtra("awayTeamID", match.idAwayTeam)
        startActivity(intent)
    }

    private fun getFavorite() {
        favoriteMatch.clear()
        context?.database?.use {
            val result = select(NextMatchFavorite.NEXT_MATCH_FAVORITE_TB)
            val favorite = result.parseList(classParser<NextMatchFavorite>())
            favoriteMatch.addAll(favorite)
            nextMatchFavoriteAdapter.notifyDataSetChanged()
            rvNextMatchFavoriteFragment.scheduleLayoutAnimation()
        }
    }

    override fun onResume() {
        super.onResume()
        getFavorite()
    }


}
