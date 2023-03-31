package com.example.afreecasampleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.afreecasampleapp.databinding.FragmentDetailBinding
import com.example.afreecasampleapp.viewmodels.AfreecaTvViewModel


class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private val args by navArgs<DetailFragmentArgs>()
    val viewModel : AfreecaTvViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding?.broadInfo = args.selectedBroad
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}