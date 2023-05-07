package com.accenture.f1app.ui.view.recyclerviewshome

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.R
import com.accenture.f1app.data.model.circuit.Circuit
import com.accenture.f1app.databinding.ItemCircuitBinding

class CircuitsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCircuitBinding.bind(view)


    fun render(circuit: Circuit, onItemSelected: (String) -> Unit){
        binding.circuitName.text = circuit.circuitName



        // Get the flag image according to the circuit's country
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
        binding.imgCircuitFlag.setImageResource(flagImage)

        //Get the image of the circuit according to their id and set it in the ImageView 'imgCircuit'
        val circuitId = circuit.circuitId
        val circuitImageResource = itemView.context.resources.getIdentifier(circuitId, "drawable", itemView.context.packageName)


        binding.imgCircuit.setImageResource(circuitImageResource)

        binding.root.setOnClickListener{onItemSelected(circuit.circuitId)}

    }
}