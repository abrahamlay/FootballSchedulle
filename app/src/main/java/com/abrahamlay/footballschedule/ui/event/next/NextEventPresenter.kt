package com.abrahamlay.footballschedule.ui.event.next

import com.abrahamlay.footballschedule.model.NextEvent
import com.abrahamlay.footballschedule.repo.APICallback
import com.abrahamlay.footballschedule.repo.ScheduleRepository
import com.abrahamlay.footballschedule.ui.event.EventContract

/**
 * Created by Abraham on 24/04/2018.
 */
class NextEventPresenter(val view: EventContract.NextEventView) :EventContract.NextEventAction{

    override fun loadNextFixture(id: Int) {
        view.showLoading(true)
        ScheduleRepository.getInstance().getNextSchedule(id,object : APICallback {
            override fun onSuccess(data: Any) {
                view.showLoading(false)
                view.onNextFixtureLoaded(data as NextEvent)
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