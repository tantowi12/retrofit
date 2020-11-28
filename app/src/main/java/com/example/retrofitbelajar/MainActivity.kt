package com.example.retrofitbelajar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<PostResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        showPost()
//        createPost()
//        updatePost()
        deletePost()
    }

    private fun deletePost() {
        RetrofitClient.instance.deletePost(1).enqueue(object :Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                tv_ResponseCode.text = t.message
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                tv_ResponseCode.text = response.code().toString()
            }

        })
    }

    private fun updatePost() {
        RetrofitClient.instance.patchPost(
                5,
                6,
                5,
                null,
                "telah berganti"

        ).enqueue(object : Callback<PostResponse>{
            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                tv_ResponseCode.text = t.message
            }

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                tv_ResponseCode.text = response.code().toString()
                val responseText = "Response Code: ${response.code()}\n" +
                        "title: ${response.body()?.title}\n" +
                        "text: ${response.body()?.text}\n"
                tv_ResponseCode.text = responseText
            }

        })
    }

    private fun createPost() {
        RetrofitClient.instance.createPost(
                29,
                "Belajar Retrofit Ya",
                "Sedang melakukan sebuah proses untuk menentukan bagaimana suatu masa bisa dilalui"
        ).enqueue(object : Callback<CreatePostResponse>{
            override fun onFailure(call: Call<CreatePostResponse>, t: Throwable) {
                tv_ResponseCode.text = t.message
            }

            override fun onResponse(call: Call<CreatePostResponse>, response: Response<CreatePostResponse>) {
                val responseText = "Response code: ${response.code()}\n" +
                        "title: ${response.body()?.title}\n" +
                        "text: ${response.body()?.text}\n"
                tv_ResponseCode.text = responseText
            }

        })
    }

    private fun showPost() {
        rv_Post.setHasFixedSize(true)
        rv_Post.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getPost().enqueue(object: Callback<ArrayList<PostResponse>>{
            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                    call: Call<ArrayList<PostResponse>>,
                    response: Response<ArrayList<PostResponse>>
            ) {
                val responseCode = response.code().toString()
                tv_ResponseCode.text = responseCode
                response.body()?.let { list.addAll(it)}
                val adapter = PostAdapter(list)
                rv_Post.adapter = adapter
            }

        } )
    }
}