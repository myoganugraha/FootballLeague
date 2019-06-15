package io.myoganugraha.footballleague.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.myoganugraha.footballleague.R
import io.myoganugraha.footballleague.database.PreviousMatchFavorite
import io.myoganugraha.footballleague.utils.DateParser

class PreviousMatchFavoriteAdapter(private val context: Context, private val match: MutableList<PreviousMatchFavorite>, private var listener: (PreviousMatchFavorite) -> Unit) : RecyclerView.Adapter<PreviousMatchFavoriteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return match.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(match[position], listener)
    }

    inner class ViewHolder(
        containerView: View
    ) : RecyclerView.ViewHolder(containerView)  {
        @SuppressLint("SimpleDateFormat")
        fun bindItem(match: PreviousMatchFavorite, listener: (PreviousMatchFavorite) -> Unit) {

            itemView.findViewById<TextView>(R.id.tv_home_team_match).text = match.strHomeTeam
            itemView.findViewById<TextView>(R.id.tv_away_team_match).text = match.strAwayTeam

            itemView.findViewById<TextView>(R.id.tv_score_home_team_match).text = match.intHomeScore
            itemView.findViewById<TextView>(R.id.tv_score_away_team_match).text = match.intAwayScore

            if (match.dateEvent == null) {
                itemView.findViewById<TextView>(R.id.matchDate).text = ""
            } else {
                itemView.findViewById<TextView>(R.id.matchDate).text = DateParser().parser(match.dateEvent)
            }

            itemView.setOnClickListener { listener(match) }
        }
    }
}