package com.accenture.f1app.ui.view.recyclerviewlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.R
import com.accenture.f1app.data.model.circuit.Circuit

class CircuitListAdapter(
    var circuits: MutableList<Circuit>,
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<CircuitListViewHolder>() {

    var filteredCircuits: MutableList<Circuit> = mutableListOf()

    init {
        filteredCircuits.addAll(circuits)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircuitListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_circuitlist, parent, false)
        return CircuitListViewHolder(view)
    }


    override fun onBindViewHolder(holder: CircuitListViewHolder, position: Int) {
        holder.bind(filteredCircuits[position], onItemSelected)
    }

    override fun getItemCount(): Int = filteredCircuits.size

    fun filter(query: String) {
        filteredCircuits.clear()
        if (query.isEmpty()) {
            filteredCircuits.addAll(circuits)
        } else {
            for (circuit in circuits) {
                if (circuit.circuitName.contains(query, true) || circuit.circuitId.contains(
                        query,
                        true
                    )
                ) {
                    filteredCircuits.add(circuit)
                }
            }
        }
        notifyDataSetChanged()
    }

    fun updateCircuits(newCircuits: List<Circuit>) {
        circuits.clear()
        circuits.addAll(newCircuits)
        filter("")
    }
}