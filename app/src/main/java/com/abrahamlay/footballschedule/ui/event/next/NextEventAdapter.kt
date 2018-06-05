package com.abrahamlay.footballschedule.ui.event.next

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abrahamlay.footballschedule.R
import com.abrahamlay.footballschedule.model.NextEventItem
import com.abrahamlay.footballschedule.ui.ItemClickListener
import kotlinx.android.synthetic.main.next_event_item_list.view.*

/**
 * Created by Abraham on 24/04/2018.
 */
class NextEventAdapter(private val context:Context?,
                       private val eventList:List<NextEventItem>?,
                       private val listener: ItemClickListener) : RecyclerView.Adapter<NextEventAdapter.NextEventViewHolder>() {
    override fun onBindViewHolder(holder: NextEventViewHolder, position: Int){
        holder.bindData(eventList?.get(position),position)
    }

    override fun getItemCount(): Int = eventList?.size!!


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextEventViewHolder =
                    NextEventViewHolder(LayoutInflater.from(context).
                        inflate(R.layout.next_event_item_list,
                                parent,
                                false),listener)


    class NextEventViewHolder(itemView: View?, private val listener: ItemClickListener) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: NextEventItem?,position:Int){
            itemView.tv_event_date.text=item?.dateEvent
            itemView.tv_home_team.text=item?.strHomeTeam
            itemView.tv_away_team.text=item?.strAwayTeam
            itemView.setOnClickListener({listener.onItemClicked(item as Any ,position)})
        }
    }
}