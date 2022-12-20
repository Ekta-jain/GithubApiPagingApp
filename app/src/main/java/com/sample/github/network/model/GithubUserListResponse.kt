package com.sample.github.network.model

data class GithubUserListResponse(
    val incomplete_results: Boolean,
    val items: List<UserListItem>,
    val total_count: Int
)