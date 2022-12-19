package com.sample.github.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sample.github.domain.UserListItem
import com.sample.github.network.GithubServiceAPI

class UserPagingSource(val githubServiceAPI: GithubServiceAPI) : PagingSource<Int, UserListItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserListItem> {
        return try {
            val position = params.key?: 1
            val response = githubServiceAPI.getUserListv2("Q",position)
            LoadResult.Page(
                data = response.body()!!.items,
                prevKey = if(position==1 ) null else -1,
                nextKey = if(position == response.body()!!.total_count) null else position+1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    // Paging spuce invalidated : we will help
    override fun getRefreshKey(state: PagingState<Int, UserListItem>): Int? {

        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)?:
            state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
        /*if (state.anchorPosition != null) {
            val anchorPage = state.closestPageToPosition(state.anchorPosition!!)
            if (anchorPage?.prevKey != null) {
                return anchorPage.prevKey!!.plus(1)
            } else if (anchorPage?.nextKey != null) {
                return anchorPage.nextKey!!.minus(1)
            }

        } else {
            return null
        }*/

    }
}