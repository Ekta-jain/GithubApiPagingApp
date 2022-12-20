package com.sample.github.network

import com.sample.github.network.model.GithubUserListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubServiceAPI {
    // Please write API's here
   // https://api.github.com/?q=Q
    //https://api.github.com/search/users?q=Q
    @GET("search/users")
    suspend fun getUserList(@Query("q")query:String): Response<GithubUserListResponse>

    @GET("search/users")
    suspend fun getUserListv2(@Query("q")query:String, @Query("page")page:Int,@Query("per_page")per_page:Int): Response<GithubUserListResponse>
}