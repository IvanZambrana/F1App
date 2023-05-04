package com.accenture.f1app.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.R
import com.accenture.f1app.core.Core
import com.accenture.f1app.data.model.circuit.Circuit
import com.accenture.f1app.data.network.F1ApiClient
import com.accenture.f1app.databinding.FragmentCircuitsBinding
import com.accenture.f1app.ui.view.recyclerviewlist.CircuitListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CircuitsFragment : Fragment() {
    private lateinit var binding: FragmentCircuitsBinding
    private var circuits = mutableListOf<Circuit>()
    private lateinit var circuitAdapter: CircuitListAdapter
    private lateinit var rvCircuit: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_circuits, container, false)

        initCircuits(rootView)
        return rootView
    }

    private fun initCircuits(rootView: View) {
        circuitAdapter = CircuitListAdapter(circuits)
        rvCircuit = rootView.findViewById(R.id.circuitList)
        rvCircuit.layoutManager =
            LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
        rvCircuit.adapter = circuitAdapter
        val apiService = Core.getRetrofit().create(F1ApiClient::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getCircuitsFromCurrentSeason()
                if (response.isSuccessful && response.body() != null && response.body()!!.MRData.CircuitTable.Circuits.isNotEmpty()) {
                    withContext(Dispatchers.Main) {
                        circuits = response.body()!!.MRData.CircuitTable.Circuits.toMutableList()
                        circuitAdapter.circuits = circuits
                        circuitAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(requireContext(), "No circuits found", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error loading data", Toast.LENGTH_SHORT).show()
            }
        }
    }

}