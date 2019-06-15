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
import io.myoganugraha.footballleague.adapter.PreviousMatchFavoriteAdapter
import io.myoganugraha.footballleague.database.PreviousMatchFavorite
import io.myoganugraha.footballleague.database.database
import io.myoganugraha.footballleague.view.MatchDetail.MatchDetail
import kotlinx.android.synthetic.main.fragment_previous_match_favorite.view.*
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
class PreviousMatchFavoriteFragment : Fragment() {

    private lateinit var rvPreviousMatchFavoriteFragment: RecyclerView
    private lateinit var previousMatchFavoriteAdapter: PreviousMatchFavoriteAdapter

    private var favoriteMatch: MutableList<PreviousMatchFavorite> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_previous_match_favorite, container, false)
        rvPreviousMatchFavoriteFragment = view.rv_previousMatchFavorite
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        previousMatchFavoriteAdapter = PreviousMatchFavoriteAdapter(this.activity!!, favoriteMatch, { match : PreviousMatchFavorite -> onItemClicked(match)})
        rvPreviousMatchFavoriteFragment.adapter = previousMatchFavoriteAdapter
        rvPreviousMatchFavoriteFragment.setHasFixedSize(true)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)
        rvPreviousMatchFavoriteFragment.layoutManager = layoutManager
    }

    private fun onItemClicked(match: PreviousMatchFavorite) {
        val intent = Intent(this.activity, MatchDetail::class.java)
        intent.putExtra("matchID", match.idEvent)
        intent.putExtra("homeTeamID", match.idHomeTeam)
        intent.putExtra("awayTeamID", match.idAwayTeam)
        startActivity(intent)
    }

    private fun getFavorite() {
        favoriteMatch.clear()
        context?.database?.use {
            val result = select(PreviousMatchFavorite.PREVIOUS_MATCH_FAVORITE_TB)
            val favorite = result.parseList(classParser<PreviousMatchFavorite>())
            favoriteMatch.addAll(favorite)
            previousMatchFavoriteAdapter.notifyDataSetChanged()
            rvPreviousMatchFavoriteFragment.scheduleLayoutAnimation()
        }
    }

    override fun onResume() {
        super.onResume()
        getFavorite()
    }


}
