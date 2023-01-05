package com.example.afreecasampleapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.afreecasampleapp.MukBangFragment
import com.example.afreecasampleapp.TalkCamFragment
import com.example.afreecasampleapp.TravelFragment

const val TALK_CAM_INDEX = 0
const val TRAVEL_INDEX = 1
const val MUKBANG_INDEX = 2

class ViewPagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        TALK_CAM_INDEX to { TalkCamFragment() },
        TRAVEL_INDEX to { TravelFragment() },
        MUKBANG_INDEX to { MukBangFragment() },
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}