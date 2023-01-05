package com.example.afreecasampleapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.afreecasampleapp.adapters.MUKBANG_INDEX
import com.example.afreecasampleapp.adapters.TALK_CAM_INDEX
import com.example.afreecasampleapp.adapters.TRAVEL_INDEX
import com.example.afreecasampleapp.adapters.ViewPagerAdapter
import com.example.afreecasampleapp.databinding.FragmentViewPagerHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerHomeFragment : Fragment() {

    lateinit var mainActivity : MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
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

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            TALK_CAM_INDEX -> getString(R.string.talk_cam)
            TRAVEL_INDEX -> getString(R.string.travel)
            MUKBANG_INDEX -> getString(R.string.mukbang_cook)
            else -> null
        }
    }
}