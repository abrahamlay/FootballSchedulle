package com.abrahamlay.footballschedule.ui.event.last

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abrahamlay.footballschedule.R
import com.abrahamlay.footballschedule.model.LastEventsItem
import com.abrahamlay.footballschedule.ui.ItemClickListener
import kotlinx.android.synthetic.main.next_event_item_list.view.*

/**
 * Created by Abraham on 24/04/2018.
 */
class LastEventAdapter(private val context: Context?,
                       private val eventList:List<LastEventsItem>?,
                       private val listener: ItemClickListener) : RecyclerView.Adapter<LastEventAdapter.LastEventViewHolder>() {
    override fun onBindViewHolder(holder: LastEventViewHolder, position: Int){
        holder.bindData(eventList?.get(position),position)
    }

    override fun getItemCount(): Int = eventList?.size!!


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastEventAdapter.LastEventViewHolder =
                    LastEventViewHolder(LayoutInflater.from(context).
                        inflate(R.layout.next_event_item_list,
                                parent,
                                false),listener)


    class LastEventViewHolder(itemView: View?, private val listener: ItemClickListener) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: LastEventsItem?, position:Int){
            itemView.tv_event_date.text=item?.dateEvent
            itemView.tv_home_team.text=item?.strHomeTeam
            itemView.tv_away_team.text=item?.strAwayTeam

            itemView.tv_score_away_team.visibility=View.VISIBLE
            itemView.tv_score_home_team.visibility=View.VISIBLE

            itemView.tv_score_home_team.text=item?.intHomeScore.toString()
            itemView.tv_score_away_team.text=item?.intAwayScore.toString()


            itemView.setOnClickListener({listener.onItemClicked(item as Any ,position)})
        }
    }
}