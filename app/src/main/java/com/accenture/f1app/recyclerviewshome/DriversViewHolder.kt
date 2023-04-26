package com.accenture.f1app.recyclerviewshome

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.Driver
import com.accenture.f1app.R

class DriversViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val driverName: TextView = view.findViewById(R.id.driverName)
    private val driverSName: TextView = view.findViewById(R.id.driverSName)
    private val driverNumber: TextView = view.findViewById(R.id.tvDriverNumber)
    private val imgDriver: ImageView = view.findViewById(R.id.imgDriver)
    private val imgDriverFlag: ImageView = view.findViewById(R.id.imgDriverFlag)
    private val driverDivider: View = view.findViewById(R.id.driverDivider)

    fun render(driver: Driver){
        driverName.text = "Fernando"
    }
}