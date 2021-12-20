package com.example.lesson2.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson2.R
import com.example.lesson2.databinding.ActivityMainBinding
import com.example.lesson2.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by viewBinding()
    private lateinit var shopItem: ShopItem
    private val RvAdapter by lazy { MainAdapter(){
        viewModel.getShopItem(it.id)
    } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        viewModel.shopItem.observe(this, Observer {
            viewModel.changeEnableState(it)
        })

        viewModel.shopList.observe(this, Observer {
            Log.e("TAG", "onCreate: ${it.size}", )
            RvAdapter.shopList = it

        })
    }
    private fun setupRecyclerView() {
        binding.mainRv.adapter =  RvAdapter
    }
}

