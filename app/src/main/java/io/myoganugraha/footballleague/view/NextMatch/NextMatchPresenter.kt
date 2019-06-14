package io.myoganugraha.footballleague.view.NextMatch

import android.content.Context
import android.widget.Toast
import io.myoganugraha.footballleague.model.Match
import io.myoganugraha.footballleague.model.MatchResponse
import io.myoganugraha.footballleague.network.BaseAPIService
import io.myoganugraha.footballleague.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NextMatchPresenter(private val context: Context, private val view: NextMatchView, private val retrofitClient: RetrofitClient) {
    fun getNextMatch(leagueID: String){
        view.showLoading()
        val baseAPIService : BaseAPIService = retrofitClient.getUrl().create(BaseAPIService::class.java)

        baseAPIService.getNextSchedule(leagueID).enqueue(object : Callback<MatchResponse> {
            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                view.hideLoading()
            }

            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                view.hideLoading()
                val data: MutableList<Match> = response.body()!!.events

                if (data == null) {
                    Toast.makeText(context, "No Next Event", Toast.LENGTH_SHORT).show()
                } else {
                    view.showNextMatch(data)
                }
            }

        })
    }
}