package com.abrahamlay.footballschedule.ui.event

import android.content.Context
import com.abrahamlay.footballschedule.db.Favorite
import com.abrahamlay.footballschedule.model.LastEvent
import com.abrahamlay.footballschedule.model.NextEvent
import com.abrahamlay.footballschedule.ui.BaseView

/**
 * Created by Abraham on 21/04/2018.
 */
class EventContract{

    interface NextEventAction{
        fun loadNextFixture(id:Int)
    }

    interface NextEventView : BaseView {
        fun onNextFixtureLoaded(nextEvent: NextEvent)
    }

    interface LastEventAction{
        fun loadLastResult(id:Int)
    }

    interface LastEventView : BaseView {
        fun onLastResultLoaded(lastEvent: LastEvent)
    }

    interface FavoriteAction{
        fun showFavorite(context: Context?)
    }

    interface FavoriteView : BaseView {
        fun onFavoriteLoaded(itemList:List<Favorite>)
    }
}