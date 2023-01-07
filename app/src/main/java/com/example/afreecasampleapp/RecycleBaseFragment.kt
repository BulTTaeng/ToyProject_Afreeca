package com.example.afreecasampleapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.afreecasampleapp.adapters.BroadPagingAdapter
import com.example.afreecasampleapp.data.Broad
import com.example.afreecasampleapp.viewmodels.AfreecaTvViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class RecycleBaseFragment<T: ViewDataBinding>(@LayoutRes private val layoutId: Int) :Fragment() {

    protected lateinit var binding: T
    private val adapter = BroadPagingAdapter()
    val viewModel : AfreecaTvViewModel by activityViewModels()
    protected lateinit var mainActivity: MainActivity
    private var broadListJob: Job? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    protected fun initRecycler(recyclerView: RecyclerView){
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(mainActivity)
    }

    protected fun getBroadListsIfNotNull(tabId : Int){
        if(viewModel.currentBroadLists[tabId] == null) getCategoryBroads(tabId)
    }

    fun getCategoryBroads(tapId: Int) {
        broadListJob?.cancel()
        broadListJob = lifecycleScope.launch {
            viewModel.getBroadList(tapId).collectLatest {
                adapter.submitData(it)
            }
        }
    }


}