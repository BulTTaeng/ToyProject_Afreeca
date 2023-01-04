package com.example.afreecasampleapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.afreecasampleapp.data.Broad
import com.example.afreecasampleapp.data.BroadCategory
import com.example.afreecasampleapp.data.repository.AfreecaTvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AfreecaTvViewModel @Inject constructor(
    private val repository: AfreecaTvRepository
) : ViewModel() {

}