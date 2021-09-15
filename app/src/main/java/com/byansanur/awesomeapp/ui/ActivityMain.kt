package com.byansanur.awesomeapp.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.byansanur.awesomeapp.R
import com.byansanur.awesomeapp.common.SetAppBar
import com.byansanur.awesomeapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityMain : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setSupportActionBar(binding.toolbar)
//
//        val setAppBar = SetAppBar(applicationContext, this)
//        setAppBar.setGlobalAppBar(
//            binding.appbar, binding.toolbar, listMenuItem
//        )


    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_home, menu)
//        itemGrid = menu?.findItem(R.id.grid_menu)
//        itemLinear = menu?.findItem(R.id.linear_menu)
//
//        itemGrid?.let { listMenuItem.add(it) }
//        itemLinear?.let { listMenuItem.add(it) }
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) {
//            R.id.grid_menu -> {
//                Toast.makeText(applicationContext, "grid", Toast.LENGTH_SHORT).show()
//            }
//            R.id.linear_menu -> {
////                setViewLinear()
//                Toast.makeText(applicationContext, "linear", Toast.LENGTH_SHORT).show()
//            }
//            else -> {
//                Log.e("TAG", "onOptionsItemSelected: no menu selected")
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
}