package com.example.afreecasampleapp.data.repository

import com.example.afreecasampleapp.api.AfreecaTvApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.Flow
import javax.inject.Inject

class AfreecaTvRepository@Inject constructor(private val service: AfreecaTvApiService) {

    fun getCategories() = flow{
        emit(service.broadCategoryList().broad_category)
    }.flowOn(Dispatchers.IO)

    companion object {
        private const val PAGE_SIZE = 20
    }

}