package com.example.afreecasampleapp.viewmodels

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.afreecasampleapp.data.Broad
import com.example.afreecasampleapp.data.BroadCategory
import com.example.afreecasampleapp.data.repository.AfreecaTvRepository
import com.example.afreecasampleapp.utility.event.MutableEventFlow
import com.example.afreecasampleapp.utility.event.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AfreecaTvViewModel @Inject constructor(
    private val repository: AfreecaTvRepository,
) : ViewModel() {

    private val _broadData = MutableEventFlow<Event>()
    val broadData = _broadData.asEventFlow()

    var categoryInfo = ArrayList<BroadCategory>()
    var currentCategoryId : Int = -1
    val currentBroadLists = ArrayList<Flow<PagingData<Broad>>?>(3)

    val isLoading = ObservableBoolean()

    init {
        for(i in 0..2){
            currentBroadLists.add(null)
        }
    }

    fun getCategories(){
        viewModelScope.launch {
            repository.getCategories().collectLatest {
                categoryInfo = it as ArrayList<BroadCategory>
                _broadData.emit(Event.BroadCategories(it))
            }
        }
    }

    fun getBroadList(tapId: Int): Flow<PagingData<Broad>> {
        isLoading.set(true)
        currentCategoryId = categoryInfo[tapId].cate_no
        val newResult: Flow<PagingData<Broad>> =
            repository.getBroadList(tapId , currentCategoryId).cachedIn(viewModelScope)
        currentBroadLists[tapId] = newResult
        isLoading.set(false)
        return newResult
    }


    sealed class Event {
        data class BroadCategories(val broadCategories : List<BroadCategory>) : Event()
        data class BroadLists(val broadLists : ArrayList<Broad>) : Event()
    }

}