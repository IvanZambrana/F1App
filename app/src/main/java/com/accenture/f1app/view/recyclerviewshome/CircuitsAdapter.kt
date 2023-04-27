package com.accenture.f1app.view.recyclerviewshome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.Circuit
import com.accenture.f1app.R

class CircuitsAdapter(private val circuits: List<Circuit>) :
    RecyclerView.Adapter<CircuitsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircuitsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_circuit, parent, false)
        return CircuitsViewHolder(view)
    }



    override fun onBindViewHolder(holder: CircuitsViewHolder, position: Int) {
        holder.render(circuits[position])
    }

    override fun getItemCount(): Int = circuits.size
}

/*
* class DriversAdapter(private val drivers: List<Driver>) :
    RecyclerView.Adapter<DriversViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriversViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_drivers, parent, false)
        return DriversViewHolder(view)
    }



    override fun onBindViewHolder(holder: DriversViewHolder, position: Int) {
        holder.render(drivers[position])
    }

    override fun getItemCount(): Int = drivers.size

}*/