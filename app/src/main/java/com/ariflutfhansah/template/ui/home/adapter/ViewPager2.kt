package com.ariflutfhansah.template.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ariflutfhansah.template.R
import com.ariflutfhansah.template.ui.home.model.SliderItem

class SliderAdapter(private val sliderItems: List<SliderItem>) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slider_item, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val item = sliderItems[position]
        holder.sliderImage.setImageResource(item.imageResId)
        holder.sliderTitle.text = item.title
    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }

    inner class SliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sliderImage: ImageView = itemView.findViewById(R.id.slider_image)
        val sliderTitle: TextView = itemView.findViewById(R.id.slider_title)
    }
}