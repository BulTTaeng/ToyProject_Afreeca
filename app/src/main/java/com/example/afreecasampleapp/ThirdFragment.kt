package com.example.afreecasampleapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.afreecasampleapp.databinding.FragmentThirdBinding


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