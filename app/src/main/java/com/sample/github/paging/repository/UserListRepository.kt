package com.sample.github.paging.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.sample.github.network.GithubServiceAPI
import com.sample.github.paging.UserPagingSource
import javax.inject.Inject

class UserListRepository @Inject constructor(
    private val githubService: GithubServiceAPI,
) {
  //suspend fun getUserList(query: String) = githubService.getUserList(query)
   fun getUserList(query: String) = Pager(
      //pageSize --how much items to be load in advance
      config = PagingConfig(pageSize = 5, maxSize = 100),
      pagingSourceFactory = {UserPagingSource(githubService)}
  ).liveData

}