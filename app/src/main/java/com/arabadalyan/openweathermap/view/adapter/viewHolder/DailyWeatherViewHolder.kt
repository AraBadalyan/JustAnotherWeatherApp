package com.arabadalyan.openweathermap.view.adapter.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.arabadalyan.domain.model.Daily
import com.arabadalyan.openweathermap.databinding.ItemDailyWeatherBinding
import com.bumptech.glide.Glide

class DailyWeatherViewHolder(private val binding: ItemDailyWeatherBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun binding(daily: Daily) {
        binding.daily = daily
        Glide.with(binding.root).load(daily.imagePath).into(binding.weatherImage)
        binding.executePendingBindings()
    }

    fun setItemClickListener(
        position: Int,
        daily: Daily,
        itemClick: (position: Int, daily: Daily) -> Unit
    ) {
        binding.root.setOnClickListener {
            itemClick(position, daily)
        }
    }
}