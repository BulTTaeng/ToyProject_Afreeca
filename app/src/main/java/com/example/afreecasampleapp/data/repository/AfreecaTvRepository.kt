package com.example.afreecasampleapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.afreecasampleapp.api.AfreecaTvApiService
import com.example.afreecasampleapp.data.Broad
import com.example.afreecasampleapp.data.pagingsource.BroadPagingSource
import com.example.afreecasampleapp.data.pagingsource.BroadPagingSource1
import com.example.afreecasampleapp.data.pagingsource.BroadPagingSource2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.Flow
import javax.inject.Inject

class AfreecaTvRepository @Inject constructor(private val service: AfreecaTvApiService) {

    fun getCategories() = flow {
        emit(service.broadCategoryList().broad_category)
    }.flowOn(Dispatchers.IO)

    fun getBroadList(tapId: Int, categoryId: Int): kotlinx.coroutines.flow.Flow<PagingData<Broad>> {
        when (tapId) {
            0 -> return Pager(
                config = PagingConfig(enablePlaceholders = false, pageSize = PAGE_SIZE),
                pagingSourceFactory = { BroadPagingSource(service, categoryId) }
            ).flow
            1 -> return Pager(
                config = PagingConfig(enablePlaceholders = false, pageSize = PAGE_SIZE),
                pagingSourceFactory = { BroadPagingSource1(service, categoryId) }
            ).flow
            2 -> return Pager(
                config = PagingConfig(enablePlaceholders = false, pageSize = PAGE_SIZE),
                pagingSourceFactory = { BroadPagingSource2(service, categoryId) }
            ).flow
            else -> return Pager(
                config = PagingConfig(enablePlaceholders = false, pageSize = PAGE_SIZE),
                pagingSourceFactory = { BroadPagingSource(service, categoryId) }
            ).flow
        }

    }

    companion object {
        const val PAGE_SIZE = 20
    }

}