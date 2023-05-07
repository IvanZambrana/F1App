package com.accenture.f1app.ui.view.recyclerviewlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.R
import com.accenture.f1app.data.model.driver.Driver
import com.accenture.f1app.databinding.ItemDriverlistBinding

class DriverListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemDriverlistBinding.bind(view)
    fun bind(driver: Driver, onItemSelected: (String) -> Unit) {
        binding.tvFamilyName.text = driver.familyName
        binding.tvGivenName.text = driver.givenName
        binding.tvDriverNumber.text = driver.permanentNumber

        //Change color in driverDivider according to their code
        val driverCode = driver.code
        val teamColor = when (driverCode) {
            "ALB", "SAR" -> R.color.williams
            "ALO", "STR" -> R.color.astonmartin
            "BOT", "ZHO" -> R.color.alfaromeo
            "DEV", "TSU" -> R.color.alphatauri
            "GAS", "OCO" -> R.color.alpine
            "HAM", "RUS" -> R.color.mercedes
            "HUL", "MAG" -> R.color.haas
            "NOR", "PIA" -> R.color.mclaren
            "PER", "VER" -> R.color.redbull
            "LEC", "SAI" -> R.color.ferrari
            else -> R.color.white
        }
        binding.driverDivider.setBackgroundResource(teamColor)


        binding.root.setOnClickListener { onItemSelected(driver.driverId) }

    }
}