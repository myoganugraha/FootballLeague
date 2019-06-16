package io.myoganugraha.footballleague.view.PreviousMatch

import android.content.Context
import com.nhaarman.mockitokotlin2.argumentCaptor
import io.myoganugraha.footballleague.model.Match
import io.myoganugraha.footballleague.model.MatchResponse
import io.myoganugraha.footballleague.network.BaseAPIService
import io.myoganugraha.footballleague.network.RetrofitClient
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreviousMatchPresenterTest {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var view: PreviousMatchView

    @Mock
    private lateinit var previousMatchPresenter: PreviousMatchPresenter

    @Mock
    private lateinit var retrofitClient: RetrofitClient

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        retrofitClient = RetrofitClient()
        previousMatchPresenter = PreviousMatchPresenter(mockContext, view, retrofitClient)
    }

    @Test
    fun getNextMatch() {
        val leagueID = "4328"
        previousMatchPresenter.getPreviousMatch(leagueID)
        val baseAPIService : BaseAPIService = retrofitClient.getUrl().create(BaseAPIService::class.java)

        argumentCaptor<PreviousMatchView>().apply {
            baseAPIService.getNextSchedule(leagueID).enqueue(object : Callback<MatchResponse> {
                override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                    Mockito.verify(t.message)
                }

                override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                    val data: MutableList<Match> = response.body()!!.events
                    firstValue.showPreviousMatch(data!!)
                    Mockito.verify(view.showPreviousMatch(data!!))
                }

            })
        }
    }

}