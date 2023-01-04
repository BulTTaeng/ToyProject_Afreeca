package com.example.afreecasampleapp.data.repository

import com.example.afreecasampleapp.api.AfreecaTvApiService
import javax.inject.Inject

class AfreecaTvRepository@Inject constructor(private val service: AfreecaTvApiService) {



    companion object {
        private const val PAGE_SIZE = 20
    }

}