package com.abrahamlay.footballschedule.ui.detail

import android.content.Context
import com.abrahamlay.footballschedule.db.Favorite
import com.abrahamlay.footballschedule.db.database
import com.abrahamlay.footballschedule.model.DetailEvent
import com.abrahamlay.footballschedule.model.Teams
import com.abrahamlay.footballschedule.repo.APICallback
import com.abrahamlay.footballschedule.repo.ScheduleRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * Created by Abraham on 24/04/2018.
 */
class DetailPresenter(val view: DetailContract.DetailView) : DetailContract.DetailAction {
    override fun loadDetail(idEvent: Int?) {
        view.showLoading(true)
        if (idEvent != null) {
            ScheduleRepository.getInstance().getDetail(idEvent,object : APICallback {
                override fun onSuccess(data: Any) {
                    view.showLoading(false)
                    view.onDetailLoaded(data as DetailEvent)
                }

                override fun onFailed(data: Any) {
                    view.showLoading(false)
                    view.showEmpty("Oops, Something Wrong Happen!")
                }

                override fun onEmpty(data: Any) {
                    view.showLoading(false)
                    view.showEmpty("Sorry, We couldn't found any Match")
                }
            })
        }
    }

    override fun lookupTeam(idTeam: Int) {
        view.showLoading(true)
        ScheduleRepository.getInstance().getTeam(idTeam,object : APICallback {
            override fun onSuccess(data: Any) {
                view.showLoading(false)
                view.onTeamLoaded(data as Teams)
            }

            override fun onFailed(data: Any) {
                view.showLoading(false)
                view.showEmpty("Oops, Something Wrong Happen!")
            }

            override fun onEmpty(data: Any) {
                view.showLoading(false)
                view.showEmpty("Sorry, We couldn't found any Match")
            }
        })
    }

    override fun loadLocalDetail(idEvent: Long?, context: Context?) {
        view.showLoading(true)
        if (idEvent != null) {
            context?.database?.use {
                val result = select(Favorite.TABLE_FAVORITE)
                        .whereArgs("(ID_EVENT = {id})",
                                "id" to idEvent)
                val favorite = result.parseList(classParser<Favorite>())
                if(!favorite.isEmpty()) {
                    view.showLoading(false)
                    view.onLocalDetailLoaded(favorite[0])
                }else{
                    view.showLoading(false)
                    view.showEmpty("Sorry, We couldn't found any Match")
                }
            }
        }
    }
}