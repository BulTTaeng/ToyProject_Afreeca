package com.example.afreecasampleapp.data.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.afreecasampleapp.api.AfreecaTvApiService
import com.example.afreecasampleapp.data.Broad
import com.example.afreecasampleapp.data.repository.AfreecaTvRepository
import com.example.afreecasampleapp.data.repository.AfreecaTvRepository.Companion.PAGE_SIZE


private const val PAGE_INDEX = 1

class BroadPagingSource2(
    private val service: AfreecaTvApiService,
    private val categoryId: Int
) : PagingSource<Int, Broad>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Broad> {
        val page = params.key ?: PAGE_INDEX

        return try {
            val response = service.broadList(select_value = categoryId, pageNo = page)
            val broads = response.broad
            LoadResult.Page(
                data = broads,
                prevKey = if (page == PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.total_cnt/ PAGE_SIZE + 1) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Broad>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}