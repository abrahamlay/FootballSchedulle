package com.abrahamlay.footballschedule.ui.event.last

import android.content.Context
import com.abrahamlay.footballschedule.db.Favorite
import com.abrahamlay.footballschedule.db.database
import com.abrahamlay.footballschedule.model.LastEvent
import com.abrahamlay.footballschedule.repo.APICallback
import com.abrahamlay.footballschedule.repo.ScheduleRepository
import com.abrahamlay.footballschedule.ui.event.EventContract
import org.jetbrains.anko.db.SqlOrderDirection
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * Created by Abraham on 24/04/2018.
 */
class FavoritePresenter(val view: EventContract.FavoriteView) : EventContract.FavoriteAction {

    override fun showFavorite(context:Context?){
        view.showLoading(true)
        context?.database?.use {
            view.showLoading(false)
            val result = select(Favorite.TABLE_FAVORITE).orderBy("ID_EVENT",SqlOrderDirection.DESC)
            val favorite = result.parseList(classParser<Favorite>())
            view.onFavoriteLoaded(favorite)
        }
    }

}