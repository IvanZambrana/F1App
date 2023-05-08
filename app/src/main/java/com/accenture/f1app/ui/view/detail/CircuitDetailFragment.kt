package com.accenture.f1app.ui.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.accenture.f1app.R
import com.accenture.f1app.core.Core
import com.accenture.f1app.data.network.F1ApiClient
import com.accenture.f1app.databinding.FragmentCircuitDetailBinding
import com.accenture.f1app.databinding.FragmentTeamDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CircuitDetailFragment : Fragment() {

    private lateinit var binding: FragmentCircuitDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCircuitDetailBinding.inflate(inflater, container, false)
        binding.progressBar.isVisible = true
        //Get Circuit Id
        val circuitId = arguments?.getString("id")
        if (circuitId == null) {
            Toast.makeText(requireContext(), "Circuit ID not found", Toast.LENGTH_SHORT).show()
        }

        //Coroutine
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = Core.getRetrofit().create(F1ApiClient::class.java)
            val response = apiService.getCircuitById(circuitId)

            withContext(Dispatchers.Main) {
                val circuit = response.body()?.MRData?.CircuitTable?.Circuits!!.first()
                binding.progressBar.isVisible = false
                binding.tvCircuitNameDetail.text = circuit.circuitName
                binding.tvCircuitCountryDetail.text = circuit.Location.country
                binding.tvCircuitLocalityDetail.text = circuit.Location.locality

                //Get the image of the circuit according to their id and set it in the ImageView 'imgCircuit'
                val circuitId = circuit.circuitId
                val circuitImageResource = context?.resources?.getIdentifier(
                    circuitId,
                    "drawable",
                    requireContext().packageName
                )


                if (circuitImageResource != null) {
                    binding.ivCircuitDetail.setImageResource(circuitImageResource)
                }
            }


        }
        return binding.root
    }


}