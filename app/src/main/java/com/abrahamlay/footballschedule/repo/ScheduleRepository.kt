package com.abrahamlay.footballschedule.repo

import android.util.Log
import com.abrahamlay.footballschedule.network.RestClient
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Abraham on 20/04/2018.
 */
class ScheduleRepository{
        private val getClient=RestClient.retrofit.create(TheSportAPI::class.java)
        private object Holder { val INSTANCE = ScheduleRepository() }
        private val context: CoroutineContextProvider = TestContextProvider()

        companion object {
            fun getInstance(): ScheduleRepository {
                 val instance: ScheduleRepository by lazy { Holder.INSTANCE }
                return instance
            }
        }
        fun getNextSchedule(id:Int,callback:APICallback){
            async(context.main) {
              bg{
                 getClient.getNextLeague(id).subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { NextEvent ->
                                Log.d("Success",NextEvent.toString())
                                if (NextEvent.events==null) {
                                    callback.onEmpty("")
                                } else {
                                    callback.onSuccess(NextEvent)
                                }
                            },
                            { error ->
                                Log.e("Error", error.message)
                                callback.onFailed(error)
                            }
                    )
                }
            }
        }

        fun getLastResult(id:Int,callback:APICallback){
            async(context.main) {
                bg{
            getClient.getLastResult(id)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { LastEvent ->
                                Log.d("Success",LastEvent.toString())
                                if (LastEvent.events==null) {
                                    callback.onEmpty("")
                                } else {
                                    callback.onSuccess(LastEvent)
                                }
                            },
                            { error ->
                                Log.e("Error", error.message)
                                callback.onFailed(error)
                            }
                    )
                }
            }
        }

        fun getDetail(id:Int,callback:APICallback){
            async(context.main) {
                bg {
                    getClient.getDetail(id)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    { DetailEvent ->
                                        Log.d("Success", DetailEvent.toString())
                                        if (DetailEvent.events.isEmpty()) {
                                            callback.onEmpty("")
                                        } else {
                                            callback.onSuccess(DetailEvent)
                                        }
                                    },
                                    { error ->
                                        Log.e("Error", error.message)
                                        callback.onFailed(error)
                                    }
                            )
                }
            }
        }

        fun getTeam(idTeam: Int, callback: APICallback) {
            async(context.main) {
                bg {
                    getClient.getTeam(idTeam)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    { Teams ->
                                        Log.d("Success", Teams.toString())
                                        if (Teams.teams.isEmpty()) {
                                            callback.onEmpty("")
                                        } else {
                                            callback.onSuccess(Teams)
                                        }
                                    },
                                    { error ->
                                        Log.e("Error", error.message)
                                        callback.onFailed(error)
                                    }
                            )
                }
            }
        }
}