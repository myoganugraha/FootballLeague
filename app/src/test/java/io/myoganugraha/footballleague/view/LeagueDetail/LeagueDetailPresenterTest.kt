package io.myoganugraha.footballleague.view.LeagueDetail

import io.myoganugraha.footballleague.model.LeagueResponse
import io.myoganugraha.footballleague.network.BaseAPIService
import io.myoganugraha.footballleague.network.RetrofitClient
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import com.nhaarman.mockitokotlin2.argumentCaptor
import io.myoganugraha.footballleague.model.League
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueDetailPresenterTest {

    @Mock
    private lateinit var view: LeagueDetailView

    @Mock
    private lateinit var leagueDetailPresenter: LeagueDetailPresenter

    @Mock
    private lateinit var retrofitClient: RetrofitClient

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        retrofitClient = RetrofitClient()
        leagueDetailPresenter = LeagueDetailPresenter(view, retrofitClient)
    }

    @Test
    fun getLeagueDetail() {
        val leagueID = "4328"
        leagueDetailPresenter.getLeagueDetail(leagueID)

        val baseAPIService : BaseAPIService = retrofitClient.getUrl().create(BaseAPIService::class.java)

        argumentCaptor<LeagueDetailView>().apply {
            baseAPIService.getLeagueDetail(leagueID).enqueue(object : Callback<LeagueResponse> {
                override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {
                    Mockito.verify(t.message)
                }

                override fun onResponse(call: Call<LeagueResponse>, response: Response<LeagueResponse>) {
                    val data: League = response.body()?.leagues!![0]
                    firstValue.showLeagueDetail(data)
                    Mockito.verify(view.showLeagueDetail(data))
                }

            })
        }
    }
}