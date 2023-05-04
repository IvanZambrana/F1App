package com.accenture.f1app.ui.view.recyclerviewlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.R
import com.accenture.f1app.data.model.team.Constructor

class TeamListAdapter(var teams: MutableList<Constructor>) : RecyclerView.Adapter<TeamListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_teamlist, parent, false)
        return TeamListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamListViewHolder, position: Int) {
        holder.bind(teams[position])
    }

    override fun getItemCount(): Int = teams.size
}