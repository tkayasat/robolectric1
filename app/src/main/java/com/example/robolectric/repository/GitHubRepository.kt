package com.example.robolectric.repository

import com.example.robolectric.model.SearchResponse
import com.example.robolectric.model.SearchResult
import com.example.robolectric.presenter.presenter.RepositoryContract
import retrofit2.Response
import kotlin.random.Random

internal class GitHubRepository(private val gitHubApi: GitHubApi) : RepositoryContract {

    override fun searchGithub(
        query: String,
        callback: RepositoryCallback,
    ) {
        callback.handleGitHubResponse(Response.success(getFakeResponse()))
    }

    private fun getFakeResponse(): SearchResponse {
        val list: MutableList<SearchResult> = mutableListOf()
        for (index in 1..100) {
            list.add(
                SearchResult(
                    id = index,
                    name = "Name: $index",
                    fullName = "FullName: $index",
                    private = Random.nextBoolean(),
                    description = "Description: $index",
                    updatedAt = "Updated: $index",
                    size = index,
                    stargazersCount = Random.nextInt(100),
                    language = "",
                    hasWiki = Random.nextBoolean(),
                    archived = Random.nextBoolean(),
                    score = index.toDouble()
                )
            )
        }
        return SearchResponse(list.size, list)
    }
}
