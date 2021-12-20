package com.example.lesson2.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson2.R
import com.example.lesson2.domain.ShopItem
import java.lang.RuntimeException

class MainAdapter(private val listener: (ShopItem)-> Unit): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var shopList = listOf<ShopItem>()
    set(value) {
        Log.e("TAG", ": ${value.size} ", )
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = when (viewType){
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            layout,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(shopList[position], listener)
    }

    override fun getItemViewType(position: Int): Int {
        val item = shopList[position]
        return if (item.enabled){
            VIEW_TYPE_ENABLED
        }else{
            VIEW_TYPE_DISABLED
        }
    }

    override fun getItemCount(): Int = shopList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val count = itemView.findViewById<TextView>(R.id.tv_count)

        fun onBind(shopItem: ShopItem, listener: (ShopItem) -> Unit) {
            name.text = shopItem.name
            count.text = shopItem.count.toString()
            itemView.setOnClickListener{
                listener.invoke(shopItem)
            }
        }

    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
    }
}