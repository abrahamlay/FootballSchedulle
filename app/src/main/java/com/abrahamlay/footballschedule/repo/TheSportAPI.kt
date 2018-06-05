package com.abrahamlay.footballschedule.repo

import com.abrahamlay.footballschedule.model.*
import kotlinx.coroutines.experimental.Deferred
import retrofit.http.GET
import retrofit.http.Query
import rx.Observable

/**
 * Created by Abraham on 20/04/2018.
 */
interface TheSportAPI{
    @GET("api/v1/json/1/eventsnextleague.php?")
    fun getNextLeague(@Query("id") id: Int):Observable<NextEvent>

    @GET("api/v1/json/1/eventspastleague.php?")
    fun getLastResult(@Query("id") id: Int):Observable<LastEvent>


    @GET("api/v1/json/1/lookupevent.php?")
    fun getDetail(@Query("id") id: Int):Observable<DetailEvent>

    @GET("api/v1/json/1/lookupteam.php?")
    fun getTeam(@Query("id") idTeam: Int): Observable<Teams>

//
//    @GET("api/v1/json/1/eventsnextleague.php?")
//    fun getNextLeague(@Query("id") id: Int): Deferred<NextEvent>
//
//    @GET("api/v1/json/1/eventspastleague.php?")
//    fun getLastResult(@Query("id") id: Int):Deferred<LastEvent>
//
//
//    @GET("api/v1/json/1/lookupevent.php?")
//    fun getDetail(@Query("id") id: Int):Deferred<DetailEvent>
//
//    @GET("api/v1/json/1/lookupteam.php?")
//    fun getTeam(@Query("id") idTeam: Int): Deferred<Teams>
}