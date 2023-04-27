package com.accenture.f1app.view.recyclerviewshome

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.Circuit
import com.accenture.f1app.R

class CircuitsViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    private val circuitName: TextView = view.findViewById(R.id.circuitName)
    private val imgCircuit: ImageView = view.findViewById(R.id.imgCircuit)
    private val imgCircuitFlag: ImageView = view.findViewById(R.id.imgCircuitFlag)

    fun render(circuit: Circuit){
        circuitName.text = "Circuit de Catalunya"
    }
}