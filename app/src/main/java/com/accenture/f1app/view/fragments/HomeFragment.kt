package com.accenture.f1app.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.Circuit
import com.accenture.f1app.Driver
import com.accenture.f1app.R
import com.accenture.f1app.Team
import com.accenture.f1app.data.CircuitRepository
import com.accenture.f1app.data.DriverRepository
import com.accenture.f1app.data.TeamRepository
import com.accenture.f1app.view.recyclerviewshome.CircuitsAdapter
import com.accenture.f1app.view.recyclerviewshome.DriversAdapter
import com.accenture.f1app.view.recyclerviewshome.TeamsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    //Drivers
    private val drivers = listOf(
        Driver.Alonso
    )
    private lateinit var driversAdapter: DriversAdapter
    private lateinit var rvDrivers: RecyclerView

    //Circuits
    private val circuits = listOf(
        Circuit.Montmelo
    )
    private lateinit var circuitsAdapter: CircuitsAdapter
    private lateinit var rvCircuits: RecyclerView

    //Teams
    private var teams = listOf(
        Team.AlfaRomeo
    )
    private lateinit var teamsAdapter: TeamsAdapter
    private lateinit var rvTeams: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        initDrivers(rootView) //Initialize drivers RecyclerView
        initCircuits(rootView) //Initialize circuits RecyclerView
        initTeams(rootView) //Initialize teams RecyclerView



        val driverRepository: DriverRepository = DriverRepository()
        val teamRepository: TeamRepository = TeamRepository()
        val circuitRepository: CircuitRepository = CircuitRepository()

        CoroutineScope(Dispatchers.IO).launch {
            driverRepository.getDriversFromCurrentSeason()
            teamRepository.getConstructorsFromCurrentSeason()
            circuitRepository.getCircuitsFromCurrentSeason()
        }

        return rootView

    }

    private fun initTeams(rootView: View) {
        teamsAdapter = TeamsAdapter(teams)
        rvTeams = rootView.findViewById(R.id.rvTeams)
        rvTeams.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvTeams.adapter = teamsAdapter
    }

    private fun initCircuits(rootView: View) {
        circuitsAdapter = CircuitsAdapter(circuits)
        rvCircuits = rootView.findViewById(R.id.rvCircuits)
        rvCircuits.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvCircuits.adapter = circuitsAdapter
    }

    private fun initDrivers(rootView: View) {
        driversAdapter = DriversAdapter(drivers)
        rvDrivers = rootView.findViewById(R.id.rvDrivers)
        rvDrivers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvDrivers.adapter = driversAdapter
    }


}