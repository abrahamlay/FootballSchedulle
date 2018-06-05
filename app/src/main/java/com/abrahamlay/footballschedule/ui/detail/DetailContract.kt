package com.abrahamlay.footballschedule.ui.detail

import android.content.Context
import com.abrahamlay.footballschedule.db.Favorite
import com.abrahamlay.footballschedule.model.DetailEvent
import com.abrahamlay.footballschedule.model.Teams
import com.abrahamlay.footballschedule.ui.BaseView

/**
 * Created by Abraham on 01/05/2018.
 */
class DetailContract{
    interface DetailAction{
        fun loadDetail(idEvent:Int?)
        fun lookupTeam(idTeam: Int)
        fun loadLocalDetail(idEvent: Long?, context: Context?)
    }

    interface DetailView :BaseView{
        fun onLocalDetailLoaded(favorite:Favorite)
        fun onDetailLoaded(data:DetailEvent)
        fun onTeamLoaded(teams: Teams)
    }
}