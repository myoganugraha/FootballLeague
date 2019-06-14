package io.myoganugraha.footballleague.View.MatchDetail

import android.util.Log
import io.myoganugraha.footballleague.Model.Match
import io.myoganugraha.footballleague.Model.MatchResponse
import io.myoganugraha.footballleague.Network.BaseAPIService
import io.myoganugraha.footballleague.Network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchDetailPresenter(private  var view: MatchDetailView, private var retrofitClient: RetrofitClient) {
    fun getMatchDetail(idEvent: String) {
        view.showLoading()
        val baseAPIService : BaseAPIService = retrofitClient.getUrl().create(BaseAPIService::class.java)

        baseAPIService.getEventDetail(idEvent).enqueue(object : Callback<MatchResponse>{
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
}