package com.accenture.f1app.ui.view.recyclerviewshome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.R
import com.accenture.f1app.data.model.team.Constructor

class TeamsAdapter(
    var teams: MutableList<Constructor>,
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<TeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return TeamsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.render(teams[position], onItemSelected)
    }

    override fun getItemCount(): Int = teams.size
}
