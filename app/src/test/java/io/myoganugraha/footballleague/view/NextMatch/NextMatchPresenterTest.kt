package io.myoganugraha.footballleague.view.NextMatch

import android.content.Context
import com.nhaarman.mockitokotlin2.argumentCaptor
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
import kotlin.coroutines.coroutineContext

class NextMatchPresenterTest {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var view: NextMatchView

    @Mock
    private lateinit var nextMatchPresenter: NextMatchPresenter

    @Mock
    private lateinit var retrofitClient: RetrofitClient

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        retrofitClient = RetrofitClient()
        nextMatchPresenter = NextMatchPresenter(mockContext, view, retrofitClient)
    }

    @Test
    fun getNextMatch() {
        val leagueID = "4328"
        nextMatchPresenter.getNextMatch(leagueID)
        val baseAPIService : BaseAPIService = retrofitClient.getUrl().create(BaseAPIService::class.java)

        argumentCaptor<NextMatchView>().apply {
            baseAPIService.getNextSchedule(leagueID).enqueue(object : Callback<MatchResponse> {
                override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                    Mockito.verify(t.message)
                }

                override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                    val data: MutableList<Match> = response.body()!!.events
                    firstValue.showNextMatch(data!!)
                    Mockito.verify(view.showNextMatch(data!!))
                }

            })
        }
    }
}