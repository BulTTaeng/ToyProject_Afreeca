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


class ThirdFragment : RecycleBaseFragment<FragmentThirdBinding>(R.layout.fragment_third) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.viewModel = viewModel
        binding.fragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler(binding.recyclerThird)
        getBroadListsIfNotNull(2)
    }
}