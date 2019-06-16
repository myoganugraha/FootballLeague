package io.myoganugraha.footballleague.view.SearchResult

import android.content.Context
import com.nhaarman.mockitokotlin2.argumentCaptor
import io.myoganugraha.footballleague.model.Match
import io.myoganugraha.footballleague.model.SearchMatchResponse
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
import retrofit2.create

class SearchResultPresenterTest {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var view: SearchResultView

    @Mock
    private lateinit var searchResultPresenter: SearchResultPresenter

    @Mock
    private lateinit var retrofitClient: RetrofitClient

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        retrofitClient = RetrofitClient()
        searchResultPresenter = SearchResultPresenter(mockContext, view, retrofitClient)
    }

    @Test
    fun searchMatch() {
        val searchQuery = "Arsenal vs chelsea"
        searchResultPresenter.searchMatch(searchQuery)

        val baseAPIService : BaseAPIService = retrofitClient.getUrl().create(BaseAPIService::class.java)

        argumentCaptor<SearchResultView>().apply {
            baseAPIService.searchMatch(searchQuery).enqueue(object : Callback<SearchMatchResponse> {
                override fun onFailure(call: Call<SearchMatchResponse>, t: Throwable) {
                    Mockito.verify(t.message)
                }

                override fun onResponse(call: Call<SearchMatchResponse>, response: Response<SearchMatchResponse>) {
                    val data: MutableList<Match> = response.body()!!.event
                    firstValue.showData(data!!)
                    Mockito.verify(view.showData(data!!))
                }

            })
        }
    }
}