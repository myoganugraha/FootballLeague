package io.myoganugraha.footballleague.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.myoganugraha.footballleague.model.Match
import io.myoganugraha.footballleague.R
import io.myoganugraha.footballleague.utils.DateParser

class MatchAdapter(private val match: MutableList<Match>, private var listener: (Match) -> Unit) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {
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
        fun bindItem(match: Match, listener: (Match) -> Unit) {

            itemView.findViewById<TextView>(R.id.tv_home_team_match).text = match.strHomeTeam
            itemView.findViewById<TextView>(R.id.tv_away_team_match).text = match.strAwayTeam

            itemView.findViewById<TextView>(R.id.tv_score_home_team_match).text = match.intHomeScore
            itemView.findViewById<TextView>(R.id.tv_score_away_team_match).text = match.intAwayScore

            itemView.findViewById<TextView>(R.id.matchDate).text = DateParser().parser(match.dateEvent)

            itemView.setOnClickListener { listener(match) }
        }
    }
}