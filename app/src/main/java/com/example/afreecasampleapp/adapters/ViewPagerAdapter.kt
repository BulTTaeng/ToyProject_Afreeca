package com.example.afreecasampleapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.afreecasampleapp.ThirdFragment
import com.example.afreecasampleapp.FirstFragment
import com.example.afreecasampleapp.SecondFragment


class ViewPagerAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        0 to { FirstFragment() },
        1 to { SecondFragment() },
        2 to { ThirdFragment() },
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}