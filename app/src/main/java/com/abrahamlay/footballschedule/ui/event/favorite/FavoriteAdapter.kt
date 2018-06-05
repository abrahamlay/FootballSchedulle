package com.abrahamlay.footballschedule.ui.event.last

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abrahamlay.footballschedule.R
import com.abrahamlay.footballschedule.db.Favorite
import com.abrahamlay.footballschedule.ui.ItemClickListener
import kotlinx.android.synthetic.main.next_event_item_list.view.*

/**
 * Created by Abraham on 24/04/2018.
 */
class FavoriteAdapter(private val context: Context?,
                      private val eventList:List<Favorite>,
                      private val listener: ItemClickListener) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int){
        holder.bindData(eventList[position],position)
    }

    override fun getItemCount(): Int = eventList.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder =
            FavoriteViewHolder(LayoutInflater.from(context).
                        inflate(R.layout.next_event_item_list,
                                parent,
                                false),listener)


    class FavoriteViewHolder(itemView: View?, private val listener: ItemClickListener) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: Favorite, position:Int){
            itemView.tv_home_team.text=item.homeTeamName
            itemView.tv_away_team.text=item.awayTeamName

            itemView.setOnClickListener({listener.onItemClicked(item ,position)})
        }
    }
}