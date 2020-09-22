package com.gka.aadpractice.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()

    private val gadsApi = Retrofit.Builder()
        .baseUrl("https://gadsapi.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val googleFormApi = Retrofit.Builder()
        .baseUrl(" https://docs.google.com/forms/d/e/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> gadsApiInstance(service: Class<T>): T {
        return gadsApi.create(service)
    }

    fun <T> googleFormApiInstance(service: Class<T>): T {
        return googleFormApi.create(service)
    }
}