package com.example.afreecasampleapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Broad (
    @field:SerializedName("broad_title") val broad_title: String,
    @field:SerializedName("broad_cate_no") val broad_cate_no : Int,
    @field:SerializedName("user_id") val user_id: String,
    @field:SerializedName("user_nick") val user_nick: String,
    @field:SerializedName("profile_img") val profile_img: String,
    @field:SerializedName("broad_thumb") val broad_thumb: String,
    @field:SerializedName("total_view_cnt") val total_view_cnt: String,
    ): Parcelable