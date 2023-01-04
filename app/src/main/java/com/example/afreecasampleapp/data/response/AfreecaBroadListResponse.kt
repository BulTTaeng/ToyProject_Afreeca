package com.example.afreecasampleapp.data.response

import com.example.afreecasampleapp.data.Broad
import com.google.gson.annotations.SerializedName

data class AfreecaBroadListResponse(
    @field:SerializedName("total_cnt") val total_cnt : Int,
    @field:SerializedName("page_no") val page_no: Int,
    @field:SerializedName("page_block") val page_block: Int,
    @field:SerializedName("broad") val broad: List<Broad>,
    @field:SerializedName("time") val time: Int,
)