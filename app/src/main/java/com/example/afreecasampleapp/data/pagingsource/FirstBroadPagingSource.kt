package com.example.afreecasampleapp.data.pagingsource


import com.example.afreecasampleapp.api.AfreecaTvApiService
import com.example.afreecasampleapp.data.Broad

class FirstBroadPagingSource(
    service: AfreecaTvApiService,
    childCategoryId : Int,
) : PagingBaseClass(service, categoryId = childCategoryId) {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Broad> {
        return super.load(params)
    }
}