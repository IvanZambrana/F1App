package com.accenture.f1app.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.R
import com.accenture.f1app.core.Core
import com.accenture.f1app.data.model.circuit.Circuit
import com.accenture.f1app.data.model.driver.Driver
import com.accenture.f1app.data.model.team.Constructor
import com.accenture.f1app.data.network.F1ApiClient
import com.accenture.f1app.databinding.FragmentHomeBinding
import com.accenture.f1app.ui.view.detail.CircuitDetailFragment
import com.accenture.f1app.ui.view.detail.DriverDetailFragment
import com.accenture.f1app.ui.view.detail.TeamDetailFragment
import com.accenture.f1app.ui.view.recyclerviewshome.CircuitsAdapter
import com.accenture.f1app.ui.view.recyclerviewshome.DriversAdapter
import com.accenture.f1app.ui.view.recyclerviewshome.TeamsAdapter
import kotlinx.coroutines.*

class HomeFragment : Fragment() {

    //Drivers
    private var drivers = mutableListOf<Driver>()
    private lateinit var driversAdapter: DriversAdapter
    private lateinit var rvDrivers: RecyclerView

    //Circuits
    private var circuits = mutableListOf<Circuit>()
    private lateinit var circuitsAdapter: CircuitsAdapter
    private lateinit var rvCircuits: RecyclerView

    //Teams
    private var teams = mutableListOf<Constructor>()
    private lateinit var teamsAdapter: TeamsAdapter
    private lateinit var rvTeams: RecyclerView

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(rootView)
        initDrivers(rootView) //Initialize drivers RecyclerView
        initCircuits(rootView) //Initialize circuits RecyclerView
        initTeams(rootView) //Initialize teams RecyclerView


        return binding.root

    }

    private fun initTeams(rootView: View) {
        binding.progressBar.isVisible = true
        teamsAdapter = TeamsAdapter(teams) { navigateToTeamDetail(it) }
        rvTeams = rootView.findViewById(R.id.rvTeams)
        rvTeams.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvTeams.adapter = teamsAdapter

        val apiService = Core.getRetrofit().create(F1ApiClient::class.java)
        // Call the API to get the current season teams
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getConstructorsFromCurrentSeason()
                if (response.isSuccessful) {
                    // Update the UI on the main thread with the team data
                    withContext(Dispatchers.Main) {
                        binding.progressBar.isVisible = false
                        teams =
                            response.body()!!.MRData.ConstructorTable.Constructors.toMutableList()
                        teamsAdapter.teams = teams
                        teamsAdapter.notifyDataSetChanged()


                    }
                } else {
                    Toast.makeText(requireContext(), "No teams found", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), " Error loading data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initCircuits(rootView: View) {
        circuitsAdapter = CircuitsAdapter(circuits) { navigateToCircuitDetail(it) }
        rvCircuits = rootView.findViewById(R.id.rvCircuits)
        rvCircuits.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvCircuits.adapter = circuitsAdapter

        val apiService = Core.getRetrofit().create(F1ApiClient::class.java)

        // Call the API to get the current season circuits
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getCircuitsFromCurrentSeason()
                if (response.isSuccessful) {
                    // Update the UI on the main thread with the circuit data
                    withContext(Dispatchers.Main) {
                        //circuitsAdapter.render(response.body()?.circuitTable?.circuits ?: emptyList())
                        circuits = response.body()!!.MRData.CircuitTable.Circuits.toMutableList()
                        circuitsAdapter.circuits = circuits
                        circuitsAdapter.notifyDataSetChanged()


                    }
                } else {
                    Toast.makeText(requireContext(), "No circuits found", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), " Error loading data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initDrivers(rootView: View) {
        driversAdapter = DriversAdapter(drivers) { navigateToDriverDetail(it) }
        rvDrivers = rootView.findViewById(R.id.rvDrivers)
        rvDrivers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvDrivers.adapter = driversAdapter

        val apiService = Core.getRetrofit().create(F1ApiClient::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getDriversFromCurrentSeason()
                if (response.isSuccessful && response.body() != null && response.body()!!.MRData.DriverTable.Drivers.isNotEmpty()) {
                    withContext(Dispatchers.Main) {
                        drivers = response.body()!!.MRData.DriverTable.Drivers.toMutableList()
                        driversAdapter.drivers = drivers
                        driversAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(requireContext(), "No drivers found", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error loading data", Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun navigateToDriverDetail(id: String) {
        val bundle = Bundle()
        bundle.putString("id", id)
        val fragment = DriverDetailFragment()
        fragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fl_wrapper, fragment)
            .addToBackStack(id)
            .commit()
    }


    private fun navigateToTeamDetail(id: String) {
        val bundle = Bundle()
        bundle.putString("id", id)
        val fragment = TeamDetailFragment()
        fragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fl_wrapper, fragment)
            .addToBackStack(id)
            .commit()
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

