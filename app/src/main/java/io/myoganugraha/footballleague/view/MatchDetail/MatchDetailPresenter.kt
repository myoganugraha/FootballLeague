package io.myoganugraha.footballleague.view.MatchDetail

import android.util.Log
import io.myoganugraha.footballclub.Model.Team
import io.myoganugraha.footballclub.Model.TeamResponse
import io.myoganugraha.footballleague.model.Match
import io.myoganugraha.footballleague.model.MatchResponse
import io.myoganugraha.footballleague.network.BaseAPIService
import io.myoganugraha.footballleague.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchDetailPresenter(private  var view: MatchDetailView, private var retrofitClient: RetrofitClient) {
    fun getMatchDetail(eventID: String) {
        view.showLoading()
        val baseAPIService : BaseAPIService = retrofitClient.getUrl().create(BaseAPIService::class.java)

        baseAPIService.getEventDetail(eventID).enqueue(object : Callback<MatchResponse>{
            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                view.hideLoading()
            }

            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                val data: Match = response.body()?.events!![0]
                Log.d("cek detail event", data.toString())
                view.showMatchDetailData(data)
                view.hideLoading()
            }

        })
    }

    fun getHomeTeamDetail(teamID: String) {
        val baseAPIService : BaseAPIService = retrofitClient.getUrl().create(BaseAPIService::class.java)
        baseAPIService.getTeamDetail(teamID).enqueue(object : Callback<TeamResponse>{
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                val data: Team = response.body()?.teams!![0]
                view.showHomeTeamData(data)
            }

        })
    }

    fun getAwayTeamDetail(teamID: String) {
        val baseAPIService : BaseAPIService = retrofitClient.getUrl().create(BaseAPIService::class.java)
        baseAPIService.getTeamDetail(teamID).enqueue(object : Callback<TeamResponse>{
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                val data: Team = response.body()?.teams!![0]
                view.showAwayTeamData(data)
            }

        })
    }
}