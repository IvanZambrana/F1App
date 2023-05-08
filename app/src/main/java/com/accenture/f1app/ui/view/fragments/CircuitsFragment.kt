package com.accenture.f1app.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.R
import com.accenture.f1app.core.Core
import com.accenture.f1app.data.model.circuit.Circuit
import com.accenture.f1app.data.network.F1ApiClient
import com.accenture.f1app.databinding.FragmentCircuitsBinding
import com.accenture.f1app.ui.view.detail.CircuitDetailFragment
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
        binding = FragmentCircuitsBinding.bind(rootView)

        //initCircuits(rootView)
        initCircuits()

        binding.svCircuit.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                circuitAdapter.filter(newText.orEmpty())
                return false
            }
        })


        //return rootView
        return binding.root
    }

    private fun initCircuits() {
        binding.progressBarCircuits.isVisible = true
        circuitAdapter = CircuitListAdapter(circuits) { navigateToCircuitDetail(it) }
        rvCircuit = binding.circuitList
        rvCircuit.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvCircuit.adapter = circuitAdapter

        val apiService = Core.getRetrofit().create(F1ApiClient::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getCircuitsFromCurrentSeason()
                if (response.isSuccessful && response.body() != null && response.body()!!.MRData.CircuitTable.Circuits.isNotEmpty()) {
                    withContext(Dispatchers.Main) {
                        binding.progressBarCircuits.isVisible = false
                        circuits = response.body()!!.MRData.CircuitTable.Circuits.toMutableList()
                        circuitAdapter.updateCircuits(circuits)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "No circuits found", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Error loading data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }


    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = Core.getRetrofit().create(F1ApiClient::class.java)
            val response = apiService.getCircuitsFromCurrentSeason(query)
            if (response.isSuccessful && response.body() != null && response.body()!!.MRData.CircuitTable.Circuits.isNotEmpty()) {
                val filteredCircuits =
                    response.body()!!.MRData.CircuitTable.Circuits.toMutableList()
                withContext(Dispatchers.Main) {
                    circuits = filteredCircuits
                    circuitAdapter.updateCircuits(circuits)
                }
            } else {
                withContext(Dispatchers.Main) {
                    circuits.clear()
                    circuitAdapter.updateCircuits(circuits)
                    Toast.makeText(requireContext(), "No circuits found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigateToCircuitDetail(id: String) {
        val bundle = Bundle()
        bundle.putString("id", id)
        val fragment = CircuitDetailFragment()
        fragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fl_wrapper, fragment)
            .addToBackStack(id)
            .commit()
    }

}