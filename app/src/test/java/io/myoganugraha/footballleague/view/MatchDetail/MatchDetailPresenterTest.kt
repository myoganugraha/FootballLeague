package io.myoganugraha.footballleague.view.MatchDetail

import com.nhaarman.mockitokotlin2.argumentCaptor
import io.myoganugraha.footballclub.Model.Team
import io.myoganugraha.footballclub.Model.TeamResponse
import io.myoganugraha.footballleague.model.Match
import io.myoganugraha.footballleague.model.MatchResponse
import io.myoganugraha.footballleague.network.BaseAPIService
import io.myoganugraha.footballleague.network.RetrofitClient

import org.junit.Test
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchDetailPresenterTest {

    @Mock
    private lateinit var view: MatchDetailView

    @Mock
    private lateinit var matchDetailPresenter: MatchDetailPresenter

    @Mock
    private lateinit var retrofitClient: RetrofitClient

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        retrofitClient = RetrofitClient()
        matchDetailPresenter = MatchDetailPresenter(view, retrofitClient)
    }

    @Test
    fun getMatchDetail() {
        val matchID = "602323"
        matchDetailPresenter.getMatchDetail(matchID)
        val baseAPIService : BaseAPIService = retrofitClient.getUrl().create(BaseAPIService::class.java)

        argumentCaptor<MatchDetailView>().apply {
            baseAPIService.getEventDetail(matchID).enqueue(object : Callback<MatchResponse> {
                override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                    Mockito.verify(t.message)
                }

                override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                    val data: Match = response.body()?.events!![0]
                    firstValue.showMatchDetailData(data)
                    Mockito.verify(view.showMatchDetailData(data))
                }

            })
        }
    }

    @Test
    fun getHomeTeamDetail() {
        val homeTeamID = "133604"
        matchDetailPresenter.getHomeTeamDetail(homeTeamID)
        val baseAPIService : BaseAPIService = retrofitClient.getUrl().create(BaseAPIService::class.java)

        argumentCaptor<MatchDetailView>().apply {
            baseAPIService.getTeamDetail(homeTeamID).enqueue(object : Callback<TeamResponse> {
                override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                    Mockito.verify(t.message)
                }

                override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                    val data: Team = response.body()?.teams!![0]
                    firstValue.showHomeTeamData(data)
                    Mockito.verify(view.showHomeTeamData(data))
                }

            })
        }
    }

    @Test
    fun getAwayTeamDetail() {
        val awayTeamID = "133610"
        matchDetailPresenter.getAwayTeamDetail(awayTeamID)
        val baseAPIService : BaseAPIService = retrofitClient.getUrl().create(BaseAPIService::class.java)

        argumentCaptor<MatchDetailView>().apply {
            baseAPIService.getTeamDetail(awayTeamID).enqueue(object : Callback<TeamResponse> {
                override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                    Mockito.verify(t.message)
                }

                override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                    val data: Team = response.body()?.teams!![0]
                    firstValue.showHomeTeamData(data)
                    Mockito.verify(view.showHomeTeamData(data))
                }

            })
        }
    }
}