package com.example.afreecasampleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.afreecasampleapp.databinding.FragmentTravelBinding

class TravelFragment : Fragment() {
    private lateinit var binding: FragmentTravelBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTravelBinding.inflate(inflater, container, false)
        return binding.root
    }
}