package com.accenture.f1app.ui.view.recyclerviewlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.R
import com.accenture.f1app.data.model.circuit.Circuit

class CircuitListAdapter(var circuits: MutableList<Circuit>) :
    RecyclerView.Adapter<CircuitListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircuitListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_circuitlist,parent, false)
        return CircuitListViewHolder(view)
    }


    override fun onBindViewHolder(holder: CircuitListViewHolder, position: Int) {
        holder.bind(circuits[position])
    }

    override fun getItemCount(): Int = circuits.size
}