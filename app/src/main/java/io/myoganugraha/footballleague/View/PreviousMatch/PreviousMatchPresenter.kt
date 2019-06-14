package io.myoganugraha.footballleague.View.PreviousMatch

import android.content.Context
import android.widget.Toast
import io.myoganugraha.footballleague.Model.Match
import io.myoganugraha.footballleague.Model.MatchResponse
import io.myoganugraha.footballleague.Network.BaseAPIService
import io.myoganugraha.footballleague.Network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreviousMatchPresenter(private val context: Context, private val view: PreviousMatchView, private val retrofitClient: RetrofitClient) {
    fun getPreviousMatch(leagueID: String){
        view.showLoading()
        val baseAPIService : BaseAPIService = retrofitClient.getUrl().create(BaseAPIService::class.java)

        baseAPIService.getLastSchedule(leagueID).enqueue(object : Callback<MatchResponse>{
            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                view.hideLoading()
            }

            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                view.hideLoading()
                val data: MutableList<Match> = response.body()!!.events

                if (data == null) {
                    Toast.makeText(context, "No Events", Toast.LENGTH_SHORT).show()
                } else {
                    view.showPreviousMatch(data)
                }
            }

        })
    }
}