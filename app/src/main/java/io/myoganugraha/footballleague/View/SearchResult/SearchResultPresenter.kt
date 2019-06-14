package io.myoganugraha.footballleague.View.SearchResult

import android.content.Context
import android.util.Log
import android.widget.Toast
import io.myoganugraha.footballleague.Model.Match
import io.myoganugraha.footballleague.Model.MatchResponse
import io.myoganugraha.footballleague.Model.SearchMatchResponse
import io.myoganugraha.footballleague.Network.BaseAPIService
import io.myoganugraha.footballleague.Network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultPresenter(private val context: Context, private val view: SearchResultView, private val retrofitClient: RetrofitClient)  {
    fun searchMatch(match: String){
        val baseAPIService : BaseAPIService = retrofitClient.getUrl().create(BaseAPIService::class.java)
        view.showLoading()
        baseAPIService.searchMatch(match).enqueue(object : Callback<SearchMatchResponse> {
            override fun onFailure(call: Call<SearchMatchResponse>, t: Throwable) {
                view.hideLoading()
                Toast.makeText(context, "Error Fetching Data", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<SearchMatchResponse>, response: Response<SearchMatchResponse>) {

                val data: MutableList<Match> = response.body()!!.event

                if (data == null) {
                    view.hideLoading()
                    Toast.makeText(context, "Data for : ${match} not found", Toast.LENGTH_SHORT).show()
                } else {
                    view.hideLoading()
                    view.showData(data)
                }
            }

        })
    }
}