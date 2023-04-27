package com.accenture.f1app.view.recyclerviewshome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.Driver
import com.accenture.f1app.R

class DriversAdapter(private val drivers: List<Driver>) :
    RecyclerView.Adapter<DriversViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriversViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_drivers, parent, false)
        return DriversViewHolder(view)
    }



    override fun onBindViewHolder(holder: DriversViewHolder, position: Int) {
        holder.render(drivers[position])
    }

    override fun getItemCount(): Int = drivers.size

}