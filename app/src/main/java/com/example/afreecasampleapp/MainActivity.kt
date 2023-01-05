package com.example.afreecasampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.afreecasampleapp.databinding.ActivityMainBinding
import com.example.afreecasampleapp.viewmodels.AfreecaTvViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val viewModel : AfreecaTvViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        //setContentView(R.layout.activity_main)
        //setContentView<ActivityMainBinding>(this, R.layout.activity_main)


    }
}