package com.abrahamlay.footballschedule.event.detail

import com.abrahamlay.footballschedule.model.*
import com.abrahamlay.footballschedule.repo.APICallback
import com.abrahamlay.footballschedule.repo.ScheduleRepository
import com.abrahamlay.footballschedule.ui.detail.DetailContract
import com.abrahamlay.footballschedule.ui.detail.DetailPresenter
import com.abrahamlay.footballschedule.ui.event.EventContract
import com.abrahamlay.footballschedule.ui.event.last.LastEventPresenter
import com.nhaarman.mockito_kotlin.argumentCaptor
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Created by Abraham on 30/05/2018.
 */
class DetailPresenterTest{

    @Mock
    private lateinit var view: DetailContract.DetailView

    @Mock
    private var scheduleRepository: ScheduleRepository= ScheduleRepository.getInstance()
    private lateinit var presenter: DetailPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(view)
    }

    @Test
    fun getTeamTest(){
        val items: MutableList<TeamsItem> = mutableListOf()
        val teams = Teams(items)
        val id= 133604

        scheduleRepository.getTeam(id,object : APICallback{
            override fun onSuccess(data: Any) {
                view.showLoading(false)
                view.onTeamLoaded(teams)
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
                    .getTeam(ArgumentMatchers.eq(id),capture())
            firstValue.onSuccess(teams)
        }

        verify(view).onTeamLoaded(teams)
        verify(view).showLoading(false)
    }

    @Test
    fun getTeamErrorTest(){
        val items: MutableList<TeamsItem> = mutableListOf()
        val teams = Teams(items)
        val id= 0

        scheduleRepository.getTeam(id,object : APICallback{
            override fun onSuccess(data: Any) {
                view.showLoading(false)
                view.onTeamLoaded(teams)
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
                    .getTeam(ArgumentMatchers.eq(id),capture())
            firstValue.onSuccess(teams)
        }

        verify(view).onTeamLoaded(teams)
        verify(view).showLoading(false)
    }



    @Test
    fun getDetailMatchTest(){
        val items: MutableList<DetailItem> = mutableListOf()
        val detailEvent = DetailEvent(items)
        val id= 441613

        scheduleRepository.getDetail(id,object : APICallback{
            override fun onSuccess(data: Any) {
                view.showLoading(false)
                view.onDetailLoaded(detailEvent)
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
                    .getDetail(ArgumentMatchers.eq(id),capture())
            firstValue.onSuccess(detailEvent)
        }

        verify(view).onDetailLoaded(detailEvent)
        verify(view).showLoading(false)
    }

    @Test
    fun getDetailMatchErrorTest(){
        val items: MutableList<DetailItem> = mutableListOf()
        val detailEvent = DetailEvent(items)
        val id= 0

        scheduleRepository.getDetail(id,object : APICallback{
            override fun onSuccess(data: Any) {
                view.showLoading(false)
                view.onDetailLoaded(detailEvent)
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
                    .getDetail(ArgumentMatchers.eq(id),capture())
            firstValue.onFailed(detailEvent)
        }

        verify(view).showEmpty("Sorry, We couldn't found any Match")
        verify(view).showLoading(false)
    }

}