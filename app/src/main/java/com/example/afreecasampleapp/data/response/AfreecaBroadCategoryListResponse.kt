package com.example.afreecasampleapp.data.response

import com.example.afreecasampleapp.data.BroadCategory
import com.google.gson.annotations.SerializedName

data class AfreecaBroadCategoryListResponse(
    @field:SerializedName("broad_category") val broad_category: List<BroadCategory>,
);