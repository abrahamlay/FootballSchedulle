package com.abrahamlay.footballschedule.event.last

import com.abrahamlay.footballschedule.model.LastEvent
import com.abrahamlay.footballschedule.model.LastEventsItem
import com.abrahamlay.footballschedule.repo.APICallback
import com.abrahamlay.footballschedule.repo.ScheduleRepository
import com.abrahamlay.footballschedule.ui.event.EventContract
import com.abrahamlay.footballschedule.ui.event.last.LastEventPresenter
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
class LastEventPresenterTest{

    @Mock
    private lateinit var view: EventContract.LastEventView

    @Mock
    private var scheduleRepository: ScheduleRepository= ScheduleRepository.getInstance()
    private lateinit var presenter: LastEventPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LastEventPresenter(view)
    }

    @Test
    fun getLastEventTest(){
        val items: MutableList<LastEventsItem> = mutableListOf()
        val lastEvent = LastEvent(items)
        val id= 4328

        scheduleRepository.getLastResult(id,object : APICallback{
            override fun onSuccess(data: Any) {
                view.showLoading(false)
                view.onLastResultLoaded(lastEvent)
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
                    .getLastResult(ArgumentMatchers.eq(id),capture())
            firstValue.onSuccess(lastEvent)
        }

        verify(view).onLastResultLoaded(lastEvent)
        verify(view).showLoading(false)
    }

    @Test
    fun getLastEventErrorTest(){
        val items: MutableList<LastEventsItem> = mutableListOf()
        val lastEvent = LastEvent(items)
        val id= 0


        scheduleRepository.getLastResult(id,object : APICallback{
            override fun onSuccess(data: Any) {
                view.showLoading(false)
                view.onLastResultLoaded(lastEvent)
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
                    .getLastResult(ArgumentMatchers.eq(id),capture())
            firstValue.onSuccess(lastEvent)
        }


        verify(view).onLastResultLoaded(lastEvent)
        verify(view).showLoading(false)
    }

}