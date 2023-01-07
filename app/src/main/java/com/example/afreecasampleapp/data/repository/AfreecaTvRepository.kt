package com.example.afreecasampleapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.afreecasampleapp.api.AfreecaTvApiService
import com.example.afreecasampleapp.data.Broad
import com.example.afreecasampleapp.data.pagingsource.FirstBroadPagingSource
import com.example.afreecasampleapp.data.pagingsource.SecondBroadPagingSource
import com.example.afreecasampleapp.data.pagingsource.ThirdBroadPagingSource
import com.example.afreecasampleapp.data.pagingsource.PagingBaseClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AfreecaTvRepository @Inject constructor(private val service: AfreecaTvApiService) {

    lateinit var pagingDataSource : PagingBaseClass

    fun getCategories() = flow {
        emit(service.broadCategoryList().broad_category)
    }.flowOn(Dispatchers.IO)

    fun getBroadList(tapId: Int, categoryId: Int): kotlinx.coroutines.flow.Flow<PagingData<Broad>> {

        when (tapId) {
            0 -> pagingDataSource = FirstBroadPagingSource(service , categoryId)
            1 -> pagingDataSource = SecondBroadPagingSource(service , categoryId)
            2 -> pagingDataSource = ThirdBroadPagingSource(service , categoryId)
            else -> throw IllegalArgumentException("Invalid membership!!");
        }

        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = PAGING_PAGE_SIZE),
            pagingSourceFactory = { pagingDataSource }
        ).flow

    }

    companion object {
        const val PAGING_PAGE_SIZE = 60
    }

}