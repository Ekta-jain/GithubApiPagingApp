package com.sample.github.domain

data class GithubUserListResponse(
    val incomplete_results: Boolean,
    val items: List<UserListItem>,
    val total_count: Int
)