package com.accenture.f1app.ui.view.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.accenture.f1app.R
import com.accenture.f1app.core.Core
import com.accenture.f1app.data.model.driver.Driver
import com.accenture.f1app.data.model.driver.DriverResponse
import com.accenture.f1app.data.model.driver.MRData
import com.accenture.f1app.data.network.F1ApiClient
import com.accenture.f1app.databinding.FragmentDriverDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DriverDetailFragment : Fragment() {

    private lateinit var binding: FragmentDriverDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDriverDetailBinding.inflate(inflater, container, false)


        //Get Driver Id
        val driverId = arguments?.getString("id")
        if (driverId == null) {
            Toast.makeText(requireContext(), "Driver ID not found", Toast.LENGTH_SHORT).show()
            return null
        }

        //Coroutine
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = Core.getRetrofit().create(F1ApiClient::class.java)
            val response = apiService.getDriverById(driverId)

            withContext(Dispatchers.Main) {
                val driver = response.body()?.MRData?.DriverTable?.Drivers!!.first()
                binding.tvGivenNameDetail.text = driver?.givenName
                binding.tvFamilyNameDetail.text = driver?.familyName
                binding.tvPermanentNumberDetail.text = driver?.permanentNumber
                binding.tvBirthDriverDetail.text = driver?.dateOfBirth
                val driverCode = driver?.code
                val driverImageName = driverCode?.lowercase()
                val driverImageResource =
                    context?.resources?.getIdentifier(
                        driverImageName,
                        "drawable",
                        requireContext().packageName
                    )
                if (driverImageResource != null) {
                    binding.ivDriverDetail.setImageResource(driverImageResource)
                }
                val nationality = driver?.nationality
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
                binding.ivDriverFlagDetail.setImageResource(flagImage)

            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}


