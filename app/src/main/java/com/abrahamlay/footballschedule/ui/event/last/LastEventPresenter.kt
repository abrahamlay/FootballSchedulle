package com.abrahamlay.footballschedule.ui.event.last

import com.abrahamlay.footballschedule.model.LastEvent
import com.abrahamlay.footballschedule.repo.APICallback
import com.abrahamlay.footballschedule.repo.ScheduleRepository
import com.abrahamlay.footballschedule.ui.event.EventContract

/**
 * Created by Abraham on 24/04/2018.
 */
class LastEventPresenter(val view: EventContract.LastEventView) : EventContract.LastEventAction {

    override fun loadLastResult(id: Int) {
        view.showLoading(true)
        ScheduleRepository.getInstance().getLastResult(id, object : APICallback {
            override fun onSuccess(data: Any) {
                view.showLoading(false)
                view.onLastResultLoaded(data as LastEvent)
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