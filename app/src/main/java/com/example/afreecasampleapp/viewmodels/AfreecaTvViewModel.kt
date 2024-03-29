package com.example.afreecasampleapp.viewmodels

import android.util.Log
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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
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

    //private val _myUiState = MutableStateFlow<List<BroadCategory>>(listOf(BroadCategory("a",1)))
    //val myUiState: StateFlow<List<BroadCategory>> = _myUiState


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
                //_myUiState.value = it
                _broadData.emit(Event.BroadCategories(it))
            }
        }
    }

    fun getBroadList(tapId: Int): Flow<PagingData<Broad>> {
        isLoading.set(true)
        currentCategoryId = categoryInfo[tapId].cate_no
        try{
            currentBroadLists[tapId] = repository.getBroadList(currentCategoryId).cachedIn(viewModelScope)
            return currentBroadLists[tapId]!!
        }catch (e : Exception){
            Log.e("getBroadList" , e.toString())
            return emptyFlow()
        }

    }


    sealed class Event {
        data class BroadCategories(val broadCategories : List<BroadCategory>) : Event()
    }

}