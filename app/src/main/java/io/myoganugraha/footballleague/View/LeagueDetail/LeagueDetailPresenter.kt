package io.myoganugraha.footballleague.View.LeagueDetail

import android.util.Log
import io.myoganugraha.footballleague.Model.League
import io.myoganugraha.footballleague.Model.LeagueResponse
import io.myoganugraha.footballleague.Network.BaseAPIService
import io.myoganugraha.footballleague.Network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueDetailPresenter(private  var view: LeagueDetailView, private var retrofitClient: RetrofitClient) {

    fun getLeagueDetail(id: String) {
        view.showLoading()
        val baseAPIService : BaseAPIService = retrofitClient.getUrl().create(BaseAPIService::class.java)

        baseAPIService.getLeagueDetail(id).enqueue(object : Callback<LeagueResponse>{
            override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {
                view.hideLoading()
            }

            override fun onResponse(call: Call<LeagueResponse>, response: Response<LeagueResponse>) {
                Log.d("cek data", response.body().toString())
                val data: League = response.body()?.leagues!![0]
                view.showLeagueDetail(data)
                view.hideLoading()
            }

        })
    }
}