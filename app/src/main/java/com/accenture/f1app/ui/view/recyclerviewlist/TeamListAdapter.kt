package com.accenture.f1app.ui.view.recyclerviewlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.R
import com.accenture.f1app.data.model.circuit.Circuit
import com.accenture.f1app.data.model.driver.Driver
import com.accenture.f1app.data.model.team.Constructor

class TeamListAdapter(
    var teams: MutableList<Constructor>,
    private val onItemSelected: (String) -> Unit
) : RecyclerView.Adapter<TeamListViewHolder>() {

    var filteredTeams: MutableList<Constructor> = mutableListOf()

    init {
        filteredTeams.addAll(teams)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_teamlist, parent, false)
        return TeamListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamListViewHolder, position: Int) {
        holder.bind(filteredTeams[position], onItemSelected)
    }

    override fun getItemCount(): Int = filteredTeams.size

    fun filter(query: String) {
        filteredTeams.clear()
        if (query.isEmpty()) {
            filteredTeams.addAll(teams)
        } else {
            for (team in teams) {
                if (team.name.contains(query, true)) {
                    filteredTeams.add(team)
                }
            }
        }
        notifyDataSetChanged()
    }

    fun updateTeams(newTeams: List<Constructor>) {
        teams.clear()
        teams.addAll(newTeams)
        filter("")
    }
}