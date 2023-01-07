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
import com.example.afreecasampleapp.databinding.FragmentThirdBinding
import com.example.afreecasampleapp.viewmodels.AfreecaTvViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding
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
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.fragment = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        getCategoryBroads(2)
    }

    private fun initRecycler(){
        binding.recyclerThird.adapter = adapter
        binding.recyclerThird.layoutManager = LinearLayoutManager(mainActivity)
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