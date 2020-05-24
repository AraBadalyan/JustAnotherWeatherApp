package com.arabadalyan.openweathermap.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arabadalyan.domain.model.Daily
import com.arabadalyan.openweathermap.databinding.ItemDailyWeatherBinding
import com.arabadalyan.openweathermap.view.adapter.viewHolder.DailyWeatherViewHolder

class DailyWeatherAdapter(
    private val dailyList: List<Daily>,
    private val itemClick: (position: Int, daily: Daily) -> Unit
) : RecyclerView.Adapter<DailyWeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemDailyWeatherBinding.inflate(layoutInflater, parent, false)
        return DailyWeatherViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return dailyList.size
    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        val daily = dailyList[position]
        holder.binding(daily)
        holder.setItemClickListener(position, daily, itemClick)
    }
}