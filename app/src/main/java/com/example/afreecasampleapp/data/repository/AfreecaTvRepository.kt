package com.example.afreecasampleapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.afreecasampleapp.api.AfreecaTvApiService
import com.example.afreecasampleapp.data.Broad
import com.example.afreecasampleapp.data.pagingsource.BroadPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.Flow
import javax.inject.Inject

class AfreecaTvRepository@Inject constructor(private val service: AfreecaTvApiService) {

    fun getCategories() = flow{
        emit(service.broadCategoryList().broad_category)
    }.flowOn(Dispatchers.IO)

    fun getBroadList(categoryId: Int): kotlinx.coroutines.flow.Flow<PagingData<Broad>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PAGE_SIZE),
            pagingSourceFactory = { BroadPagingSource(service, categoryId) }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 20
    }

}