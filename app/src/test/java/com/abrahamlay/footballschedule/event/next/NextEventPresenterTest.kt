package com.abrahamlay.footballschedule.event.next

import com.abrahamlay.footballschedule.model.LastEvent
import com.abrahamlay.footballschedule.model.LastEventsItem
import com.abrahamlay.footballschedule.model.NextEvent
import com.abrahamlay.footballschedule.model.NextEventItem
import com.abrahamlay.footballschedule.repo.APICallback
import com.abrahamlay.footballschedule.repo.ScheduleRepository
import com.abrahamlay.footballschedule.ui.event.EventContract
import com.abrahamlay.footballschedule.ui.event.last.LastEventPresenter
import com.abrahamlay.footballschedule.ui.event.next.NextEventPresenter
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Created by Abraham on 30/05/2018.
 */
class NextEventPresenterTest{

    @Mock
    private lateinit var view: EventContract.NextEventView

    @Mock
    private var scheduleRepository: ScheduleRepository= ScheduleRepository.getInstance()
    private lateinit var presenter: NextEventPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextEventPresenter(view)
    }

    @Test
    fun getLastEventTest(){
        val items: MutableList<NextEventItem> = mutableListOf()
        val nextEvent = NextEvent(items)
        val id= 4328

        scheduleRepository.getNextSchedule(id,object : APICallback{
            override fun onSuccess(data: Any) {
                view.showLoading(false)
                view.onNextFixtureLoaded(nextEvent)
            }

            override fun onFailed(data: Any) {
                view.showLoading(false)
                view.showEmpty("Sorry, We couldn't found any Match")
            }

            override fun onEmpty(data: Any) {
                view.showLoading(false)
                view.showEmpty("Sorry, We couldn't found any Match")
            }

        })


        argumentCaptor<APICallback>().apply {
            verify(scheduleRepository)
                    .getNextSchedule(ArgumentMatchers.eq(id),capture())
            firstValue.onSuccess(nextEvent)
        }

        verify(view).onNextFixtureLoaded(nextEvent)
        verify(view).showLoading(false)
    }

    @Test
    fun getNextEventErrorTest(){
        val items: MutableList<NextEventItem> = mutableListOf()
        val nextEvent = NextEvent(items)
        val id= 0


        scheduleRepository.getNextSchedule(id,object : APICallback{
            override fun onSuccess(data: Any) {
                view.showLoading(false)
                view.onNextFixtureLoaded(nextEvent)
            }

            override fun onFailed(data: Any) {
                view.showLoading(false)
                view.showEmpty("Sorry, We couldn't found any Match")
            }

            override fun onEmpty(data: Any) {
                view.showLoading(false)
                view.showEmpty("Sorry, We couldn't found any Match")
            }

        })

        argumentCaptor<APICallback>().apply {
            verify(scheduleRepository)
                    .getNextSchedule(ArgumentMatchers.eq(id),capture())
            firstValue.onSuccess(nextEvent)
        }


        verify(view).onNextFixtureLoaded(nextEvent)
        verify(view).showLoading(false)
    }

}