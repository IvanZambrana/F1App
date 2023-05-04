package com.accenture.f1app.ui.view.recyclerviewshome

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.R
import com.accenture.f1app.data.model.team.Constructor
import com.accenture.f1app.databinding.ItemTeamBinding

class TeamsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemTeamBinding.bind(view)


    fun render(constructor: Constructor){
        binding.teamName.text = constructor.name

        //Get the image of the circuit according to their id and set it in the ImageView 'imgTeam'
        val teamId = constructor.constructorId
        val teamImageResource = itemView.context.resources.getIdentifier(teamId, "drawable", itemView.context.packageName)


        binding.imgTeam.setImageResource(teamImageResource)

    }
}