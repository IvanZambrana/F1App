package com.accenture.f1app.ui.view.recyclerviewshome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import com.accenture.f1app.Driver

import com.accenture.f1app.R
import com.accenture.f1app.data.model.driver.Driver

class DriversAdapter(
    var drivers: MutableList<Driver>,
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<DriversViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriversViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_drivers, parent, false)
        return DriversViewHolder(view)
    }


    override fun onBindViewHolder(holder: DriversViewHolder, position: Int) {
        holder.bind(drivers[position], onItemSelected)

    }

    override fun getItemCount(): Int = drivers.size

}