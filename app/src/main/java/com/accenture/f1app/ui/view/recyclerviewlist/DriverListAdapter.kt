package com.accenture.f1app.ui.view.recyclerviewlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.R
import com.accenture.f1app.data.model.driver.Driver
import com.accenture.f1app.ui.view.recyclerviewshome.DriversViewHolder

class DriverListAdapter(var drivers: MutableList<Driver>) :
    RecyclerView.Adapter<DriverListViewHolder>() {

    var filteredDrivers: MutableList<Driver> = mutableListOf()

    init {
        filteredDrivers.addAll(drivers)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_driverlist, parent, false)
        return DriverListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DriverListViewHolder, position: Int) {
        holder.bind(filteredDrivers[position])
    }

    override fun getItemCount(): Int = filteredDrivers.size

    fun filter(query: String) {
        filteredDrivers.clear()
        if (query.isEmpty()) {
            filteredDrivers.addAll(drivers)
        } else {
            for (driver in drivers) {
                if (driver.givenName.contains(query, true) || driver.familyName.contains(query, true)) {
                    filteredDrivers.add(driver)
                }
            }
        }
        notifyDataSetChanged()
    }

    fun updateDrivers(newDrivers: List<Driver>) {
        drivers.clear()
        drivers.addAll(newDrivers)
        filter("")
    }
}