package com.accenture.f1app.ui.view.recyclerviewlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.R
import com.accenture.f1app.data.model.circuit.Circuit
import com.accenture.f1app.databinding.ItemCircuitlistBinding

class CircuitListViewHolder (view: View) : RecyclerView.ViewHolder(view){

    private val binding = ItemCircuitlistBinding.bind(view)

    fun bind(circuit: Circuit, onItemSelected: (String) -> Unit){
        binding.tvCircuitName.text = circuit.circuitName

        // Get the flag image according to the driver's nationality
        val country = circuit.Location.country
        val flagImage = when(country){
            "UK" -> R.drawable.unitedkingdom
            "Italy" -> R.drawable.italy
            "Spain" -> R.drawable.spain
            "Mexico" -> R.drawable.mexico
            "Netherlands" -> R.drawable.netherlands
            "Monaco" -> R.drawable.monaco_flag1
            "Canada" -> R.drawable.canada
            "Austria" -> R.drawable.austria
            "Australia" -> R.drawable.australia
            "Japan" -> R.drawable.japan
            "Belgium" -> R.drawable.belgium
            "UAE" -> R.drawable.uae
            "USA", "United States" -> R.drawable.unitedstates
            "Singapore" -> R.drawable.singapore
            "Qatar" -> R.drawable.qatar
            "Saudi Arabia" -> R.drawable.saudiarabia
            "Brazil" -> R.drawable.brazil
            "Azerbaijan" -> R.drawable.azerbaijan
            "Hungary" -> R.drawable.hungary
            "Bahrain" -> R.drawable.bahrain_flag1


            else -> R.drawable.ic_launcher_background
        }
        binding.ivCircuitFlag.setImageResource(flagImage)

        binding.root.setOnClickListener{onItemSelected(circuit.circuitId)}
    }
}