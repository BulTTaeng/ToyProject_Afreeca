package com.example.afreecasampleapp.data.pagingsource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.afreecasampleapp.api.AfreecaTvApiService
import com.example.afreecasampleapp.data.Broad
import com.example.afreecasampleapp.data.repository.AfreecaTvRepository
import com.example.afreecasampleapp.data.repository.AfreecaTvRepository.Companion.PAGE_SIZE

class ThirdBroadPagingSource(
    service: AfreecaTvApiService,
    childCategoryId : Int,
) : PagingBaseClass(service, categoryId = childCategoryId) {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Broad> {
        return super.load(params)
    }
}