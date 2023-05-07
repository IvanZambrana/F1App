package com.accenture.f1app.ui.view.recyclerviewshome

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
//import com.accenture.f1app.Driver
import com.accenture.f1app.R
import com.accenture.f1app.data.model.driver.Driver
import com.accenture.f1app.databinding.ItemDriversBinding

class DriversViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemDriversBinding.bind(view)

    fun bind(driver: Driver, onItemSelected: (String) -> Unit) {
        binding.driverName.text = driver.givenName
        binding.driverSName.text = driver.familyName
        binding.tvDriverNumber.text = driver.permanentNumber

        // Get the flag image according to the driver's nationality
        val nationality = driver.nationality
        val flagImage = when (nationality) {
            "British" -> R.drawable.unitedkingdom
            "German" -> R.drawable.germany
            "Spanish" -> R.drawable.spain
            "Mexican" -> R.drawable.mexico
            "Dutch" -> R.drawable.netherlands
            "Monegasque" -> R.drawable.monaco_flag1
            "Canadian" -> R.drawable.canada
            "French" -> R.drawable.france
            "Australian" -> R.drawable.australia
            "Japanese" -> R.drawable.japan
            "Finnish" -> R.drawable.finland
            "Thai" -> R.drawable.thailand
            "Danish" -> R.drawable.denmark
            "Chinese" -> R.drawable.china
            "American" -> R.drawable.unitedstates
            else -> R.drawable.ic_launcher_background
        }
        binding.imgDriverFlag.setImageResource(flagImage)

        //Get the image of the driver according to their code and set it in the ImageView 'imgDriver'
        val driverCode = driver.code
        val driverImageName = driverCode.lowercase()
        val driverImageResource = itemView.context.resources.getIdentifier(
            driverImageName,
            "drawable",
            itemView.context.packageName
        )
        binding.imgDriver.setImageResource(driverImageResource)

        //Change color in driverDivider according to their code

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