package com.example.afreecasampleapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.afreecasampleapp.api.AfreecaTvApiService
import com.example.afreecasampleapp.data.Broad
import com.example.afreecasampleapp.data.pagingsource.BroadPagingSource
import com.example.afreecasampleapp.data.pagingsource.PagingBaseClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AfreecaTvRepository @Inject constructor(private val service: AfreecaTvApiService) {

    lateinit var pagingDataSource: PagingBaseClass

    fun getCategories() = flow {
        emit(service.broadCategoryList().broad_category)
    }.flowOn(Dispatchers.IO)

    fun getBroadList(tabId: Int, categoryId: Int): kotlinx.coroutines.flow.Flow<PagingData<Broad>> {
        pagingDataSource = BroadPagingSource(service, categoryId)

        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PAGING_PAGE_SIZE, prefetchDistance = 1),
            pagingSourceFactory = { pagingDataSource }
        ).flow
    }

    companion object {
        const val PAGING_PAGE_SIZE = 60
    }

}