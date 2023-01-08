package com.example.afreecasampleapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.afreecasampleapp.databinding.ActivityMainBinding
import com.example.afreecasampleapp.utility.network.NetworkConnection
import com.example.afreecasampleapp.viewmodels.AfreecaTvViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    val viewModel : AfreecaTvViewModel by viewModels()
    private lateinit var connection : NetworkConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        if(!(applicationContext as HomeApplication).isOnline()){
            Toast.makeText(applicationContext , R.string.check_internet_connection , Toast.LENGTH_LONG).show()
            exitApp()
        }
        else{
            connection = NetworkConnection(applicationContext)
            connection.observeForever( Observer { isConnected ->
                if (!isConnected)
                {
                    checkInternet()
                }
            })
        }


    }

    fun checkInternet(){
        if(!(applicationContext as HomeApplication).isOnline()) {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.check_internet_connection))
                .setPositiveButton(getString(R.string.retry)) { dialogInterface: DialogInterface, i: Int ->
                    checkInternet()
                }
                .setNegativeButton(getString(R.string.close_app)) { dialogInterface: DialogInterface, i: Int ->
                    exitApp()
                }.setCancelable(false).show()
        }
    }

    fun exitApp(){
        moveTaskToBack(true)					// 태스크를 백그라운드로 이동
        finishAndRemoveTask()					// 액티비티 종료 & 태스크 리스트에서 지우기
        android.os.Process.killProcess(android.os.Process.myPid())	// 앱 프로세스 종료
    }
}