package com.example.robolectric.view.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.robolectric.R
import com.example.robolectric.model.SearchResult
import com.example.robolectric.presenter.presenter.search.PresenterSearchContract
import com.example.robolectric.presenter.presenter.search.SearchPresenter
import com.example.robolectric.repository.GitHubApi
import com.example.robolectric.repository.GitHubRepository
import com.example.robolectric.view.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


abstract class MainActivity : AppCompatActivity(), ViewSearchContract {

    private val adapter = SearchResultAdapter()
    private val presenter: PresenterSearchContract = SearchPresenter(this)
    private var totalCount: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUI()
    }

    private fun setUI() {
        toDetailsActivityButton.setOnClickListener {
            startActivity(DetailsActivity.getIntent(this, totalCount))
        }
        setQueryListener()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    private fun setQueryListener() {
        searchEditText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = searchEditText.text.toString()
                if (query.isNotBlank()) {
                    presenter.searchGitHub(query)
                    return@OnEditorActionListener true
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.enter_search_word),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@OnEditorActionListener false
                }
            }
            false
        })
    }

    private fun createRepository(): GitHubRepository {
        return GitHubRepository(createRetrofit().create(GitHubApi::class.java))
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun displaySearchResults(
        searchResults: List<SearchResult>,
        totalCount: Int,
    ) {
        this.totalCount = totalCount
        adapter.updateResults(searchResults)
    }

    override fun displayError() {
        Toast.makeText(this, getString(R.string.undefined_error), Toast.LENGTH_SHORT).show()
    }

    override fun displayError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun displayLoading(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val BASE_URL = "https://api.github.com"
        const val FAKE = "FAKE"
    }
}

