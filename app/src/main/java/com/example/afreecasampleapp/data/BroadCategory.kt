package com.example.afreecasampleapp.data

import com.google.gson.annotations.SerializedName

data class BroadCategory (
    @field:SerializedName("cate_name") val cate_name: String,
    @field:SerializedName("cate_no") val cate_no : Int,
        )