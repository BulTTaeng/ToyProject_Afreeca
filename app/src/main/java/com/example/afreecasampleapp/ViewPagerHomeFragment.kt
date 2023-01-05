package com.example.afreecasampleapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.afreecasampleapp.adapters.ViewPagerAdapter
import com.example.afreecasampleapp.databinding.FragmentViewPagerHomeBinding
import com.example.afreecasampleapp.viewmodels.AfreecaTvViewModel
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerHomeFragment : Fragment() {

    lateinit var mainActivity : MainActivity
    val viewModel : AfreecaTvViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentViewPagerHomeBinding.inflate(inflater, container, false)
        binding.viewPagerHome.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabar, binding.viewPagerHome) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            else -> null
        }
    }
}