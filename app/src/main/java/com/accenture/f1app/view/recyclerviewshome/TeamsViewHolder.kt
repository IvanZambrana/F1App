package com.accenture.f1app.view.recyclerviewshome

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.Circuit
import com.accenture.f1app.R
import com.accenture.f1app.Team

class TeamsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val teamName: TextView = view.findViewById(R.id.teamName)
    private val imgTeam: ImageView = view.findViewById(R.id.imgTeam)

    fun render(team: Team){
        teamName.text = "Alfa romeo stake f1 team"
    }
}