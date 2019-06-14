package io.myoganugraha.footballleague.Network

import io.myoganugraha.footballleague.Model.LeagueResponse
import io.myoganugraha.footballleague.Model.MatchResponse
import io.myoganugraha.footballleague.Model.SearchMatchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseAPIService {
    @GET("api/v1/json/1/lookupleague.php")
    fun getLeagueDetail(@Query("id") leagueID : String) : Call<LeagueResponse>

    @GET("api/v1/json/1/eventsnextleague.php")
    fun getNextSchedule(@Query("id") leagueID : String) : Call<MatchResponse>

    @GET("api/v1/json/1/eventspastleague.php")
    fun getLastSchedule(@Query("id") LeagueID : String) : Call<MatchResponse>

    @GET("api/v1/json/1/searchevents.php")
    fun searchMatch(@Query("e") query : String) : Call<SearchMatchResponse>

    @GET("api/v1/json/1/lookupevent.php")
    fun getEventDetail(@Query("id") id_event : String) : Call<MatchResponse>
}
