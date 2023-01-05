package com.example.afreecasampleapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.afreecasampleapp.adapters.BroadPagingAdapter
import com.example.afreecasampleapp.databinding.FragmentSecondBinding
import com.example.afreecasampleapp.viewmodels.AfreecaTvViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var mainActivity: MainActivity
    val viewModel : AfreecaTvViewModel by activityViewModels()

    private var broadListJob: Job? = null
    private val adapter = BroadPagingAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        getCategoryBroads(viewModel.categoryInfo[1].cate_no)
    }

    private fun initRecycler(){
        binding.recyclerSecond.adapter = adapter
        binding.recyclerSecond.layoutManager = LinearLayoutManager(mainActivity)
    }

    private fun getCategoryBroads(categoryId: Int) {
        broadListJob?.cancel()
        broadListJob = lifecycleScope.launch {
            viewModel.getBroadList(categoryId).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}