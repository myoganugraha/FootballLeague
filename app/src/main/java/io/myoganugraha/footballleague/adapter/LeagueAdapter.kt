package io.myoganugraha.footballleague.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.myoganugraha.footballleague.view.Main.MainActivity
import io.myoganugraha.footballleague.model.LeagueModel
import io.myoganugraha.footballleague.R
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.AnkoContext

class LeagueAdapter(private var list: MutableList<LeagueModel>, private var listener: (LeagueModel) -> Unit) :
    RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MainActivity.LeagueItem().createView(AnkoContext.Companion.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position], listener)
    }

    inner class ViewHolder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(leagueModel: LeagueModel, listener: (LeagueModel) -> Unit) {
            if (leagueModel.leagueImage!=null){
                leagueModel.leagueImage.let { Picasso.get().load(it).into(itemView.findViewById<ImageView>(R.id.id_league_badge)) }
            }else{
                Picasso.get().load(R.drawable.ic_launcher_background).into(itemView.findViewById<ImageView>(R.id.id_league_badge))
            }
            itemView.findViewById<TextView>(R.id.id_league_name).text = leagueModel.leagueName
            itemView.setOnClickListener { listener(leagueModel) }
        }
    }
}