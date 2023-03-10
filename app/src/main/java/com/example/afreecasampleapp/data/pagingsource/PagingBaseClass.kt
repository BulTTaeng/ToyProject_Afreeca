package com.example.afreecasampleapp.data.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.afreecasampleapp.api.AfreecaTvApiService
import com.example.afreecasampleapp.data.Broad
import com.example.afreecasampleapp.data.repository.AfreecaTvRepository.Companion.PAGING_PAGE_SIZE
import kotlinx.coroutines.delay


abstract class PagingBaseClass(
    private val service: AfreecaTvApiService,
    open val categoryId: Int
) : PagingSource<Int, Broad>() {

    open val pageIndex: Int get() { return 1}
    var endPage = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Broad> {
        val page = params.key ?: pageIndex
        return try {
            val response = service.broadList(select_value = categoryId, pageNo = page)
            val broads = response.broad
            endPage = response.total_cnt/PAGING_PAGE_SIZE
            if(response.total_cnt%PAGING_PAGE_SIZE != 0 ) endPage++

            LoadResult.Page(
                data = broads,
                prevKey = if (page == pageIndex) null else page - 1,
                nextKey = if (page == endPage) null else page + 1
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