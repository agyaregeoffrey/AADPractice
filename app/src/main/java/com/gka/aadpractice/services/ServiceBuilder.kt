package com.gka.aadpractice.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gadsapi.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val form = Retrofit.Builder()
        .baseUrl(" https://docs.google.com/forms/d/e/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> retrofitInstance(service: Class<T>): T {
        return retrofit.create(service)
    }

    fun<T> formInstance(service: Class<T>): T {
        return form.create(service)
    }
}