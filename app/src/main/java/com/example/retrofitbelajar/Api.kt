package com.example.retrofitbelajar

import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET("posts")
    fun getPost(): Call<ArrayList<PostResponse>>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(
            @Field("userId") userId: Int,
            @Field("title") title: String,
            @Field("body") text: String
    ): Call<CreatePostResponse>

    @FormUrlEncoded
    @PUT("posts/{id}")
    fun putPost(
            @Path("id") id: Int,
            @Field("userId") userId: Int,
            @Field("id") idField: Int,
            @Field("title") title: String?,
            @Field("body") text: String?
    ): Call<PostResponse>

    @FormUrlEncoded
    @PATCH("posts/{id}")
    fun patchPost(
            @Path("id") id: Int,
            @Field("userId") userId: Int,
            @Field("id") idField: Int,
            @Field("title") title: String?,
            @Field("body") text: String?
    ): Call<PostResponse>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id: Int):Call<Void>
}