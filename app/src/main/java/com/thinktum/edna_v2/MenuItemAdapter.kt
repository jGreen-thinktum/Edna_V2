package com.thinktum.edna_v2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_menu_item.view.*

class MenuItemAdapter(var menuList: List<MenuItem>) : RecyclerView.Adapter<MenuItemAdapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_menu_item,
        parent, false)

        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = menuList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView_itemName.text = currentItem.itemTitle
        holder.textView_itemPrice.text = currentItem.itemPrice
    }

    override fun getItemCount() = menuList.size

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView) {

        val imageView: ImageView = itemView.imageView_itemImage
        val textView_itemName: TextView = itemView.textView_itemName
        val textView_itemPrice: TextView = itemView.textView_itemPrice

    }
}