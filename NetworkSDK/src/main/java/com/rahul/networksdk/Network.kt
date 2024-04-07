package com.rahul.networksdk

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    private var retrofit: Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()

    val getNetwork: Retrofit
        get() {
            if (retrofit == null) {
                synchronized(Retrofit::class.java) {
                    if (retrofit == null) {

                        val httpLoggingInterceptor =
                            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

                        val client =
                            OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

                        retrofit = Retrofit.Builder()
                            .baseUrl("https://api.themoviedb.org/3/")
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .client(client)
                            .build()
                    }
                }

            }
            return retrofit!!
        }
}