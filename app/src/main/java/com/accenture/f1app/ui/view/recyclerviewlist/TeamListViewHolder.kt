package com.accenture.f1app.ui.view.recyclerviewlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.R
import com.accenture.f1app.data.model.team.Constructor
import com.accenture.f1app.databinding.ItemTeamlistBinding

class TeamListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemTeamlistBinding.bind(view)

    fun bind(team: Constructor, onItemSelected: (String) -> Unit) {
        binding.tvteamName.text = team.name

        //Get the image of the team according to their id and set it in the ImageView 'imgTeam'
        val teamId = team.constructorId
        val teamImageResource = itemView.context.resources.getIdentifier(teamId, "drawable", itemView.context.packageName)
        binding.ivTeamImage.setImageResource(teamImageResource)


        //Change color in teamDivider according to their Idd
        val teamColor = when (teamId) {
            "williams" -> R.color.williams
            "aston_martin" -> R.color.astonmartin
            "alfa" -> R.color.alfaromeo
            "alphatauri" -> R.color.alphatauri
            "alpine" -> R.color.alpine
            "mercedes" -> R.color.mercedes
            "haas" -> R.color.haas
            "mclaren" -> R.color.mclaren
            "red_bull" -> R.color.redbull
            "ferrari" -> R.color.ferrari
            else -> R.color.white
        }
        binding.teamDivider.setBackgroundResource(teamColor)

        binding.root.setOnClickListener{onItemSelected(team.constructorId)}

    }
}