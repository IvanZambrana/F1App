package com.accenture.f1app.ui.view.recyclerviewshome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.R
import com.accenture.f1app.data.model.circuit.Circuit

class CircuitsAdapter(var circuits: MutableList<Circuit>) :
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

