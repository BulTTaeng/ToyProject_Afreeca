package com.example.afreecasampleapp.api

import com.example.afreecasampleapp.BuildConfig
import com.example.afreecasampleapp.data.response.AfreecaBroadCategoryListResponse
import com.example.afreecasampleapp.data.response.AfreecaBroadListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AfreecaTvApiService {

    @GET("broad/list")
    suspend fun broadList(
        @Query("client_id") clientId: String = "af_mobilelab_dev_e0f147f6c034776add2142b425e81777",
        @Query("page_no") pageNo : Int,
        @Query("select_value") select_value : Int
    ): AfreecaBroadListResponse

    @GET("broad/category/list")
    suspend fun broadCategoryList(
        @Query("client_id") clientId: String = "af_mobilelab_dev_e0f147f6c034776add2142b425e81777",
        @Query("locale") locale: String = "ko_KR"
    ): AfreecaBroadCategoryListResponse

    companion object {
        private const val BASE_URL = "https://openapi.afreecatv.com/"

        fun create(): AfreecaTvApiService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()


            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AfreecaTvApiService::class.java)
        }
    }
}