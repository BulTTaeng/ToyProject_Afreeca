package com.example.afreecasampleapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.afreecasampleapp.adapters.ViewPagerAdapter
import com.example.afreecasampleapp.data.BroadCategory
import com.example.afreecasampleapp.databinding.FragmentViewPagerHomeBinding
import com.example.afreecasampleapp.utility.event.repeatOnStarted
import com.example.afreecasampleapp.viewmodels.AfreecaTvViewModel
import com.google.android.material.tabs.TabLayoutMediator

import kotlinx.coroutines.flow.collectLatest

class ViewPagerHomeFragment : Fragment() {

    lateinit var mainActivity : MainActivity
    lateinit var binding : FragmentViewPagerHomeBinding
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
        binding = FragmentViewPagerHomeBinding.inflate(inflater, container, false)
        binding.viewPagerHome.adapter = ViewPagerAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repeatOnStarted {
            viewModel.broadData.collectLatest { event ->
                //binding.progressBarMountain.visibility = View.VISIBLE
                handleEvent(event)
            }
        }

        viewModel.getCategories()
    }

    private fun setAdapterText(categories : List<BroadCategory>){
        TabLayoutMediator(binding.tabar, binding.viewPagerHome) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }



    private fun getTabTitle(position: Int): String? {
        return when (position) {
            position -> viewModel.categoryInfo[position].cate_name
            else -> null
        }
    }

    fun handleEvent(event: AfreecaTvViewModel.Event) = when (event) {
        is AfreecaTvViewModel.Event.BroadCategories -> setAdapterText(event.mountains)
        else ->{}
    }
}