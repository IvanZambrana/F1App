package com.accenture.f1app.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.R
import com.accenture.f1app.core.Core
import com.accenture.f1app.data.model.team.Constructor
import com.accenture.f1app.data.network.F1ApiClient
import com.accenture.f1app.databinding.FragmentDriversBinding
import com.accenture.f1app.databinding.FragmentTeamsBinding
import com.accenture.f1app.ui.view.detail.TeamDetailFragment
import com.accenture.f1app.ui.view.recyclerviewlist.DriverListAdapter
import com.accenture.f1app.ui.view.recyclerviewlist.TeamListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeamsFragment : Fragment() {

    private lateinit var binding: FragmentTeamsBinding
    private var teams = mutableListOf<Constructor>()
    private lateinit var teamsAdapter: TeamListAdapter
    private lateinit var rvTeams: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_teams, container, false)
        binding = FragmentTeamsBinding.bind(rootView)
        initTeams()

        binding.svTeams.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                teamsAdapter.filter(newText.orEmpty())
                return false
            }
        })


        return binding.root
    }

    private fun initTeams() {
        binding.progressBarTeams.isVisible = true
        teamsAdapter = TeamListAdapter(teams) { navigateToTeamDetail(it) }
        rvTeams = binding.teamList
        rvTeams.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvTeams.adapter = teamsAdapter

        val apiService = Core.getRetrofit().create(F1ApiClient::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getConstructorsFromCurrentSeason()
                if (response.isSuccessful && response.body() != null && response.body()!!.MRData.ConstructorTable.Constructors.isNotEmpty()) {
                    withContext(Dispatchers.Main) {
                        binding.progressBarTeams.isVisible = false
                        teams =
                            response.body()!!.MRData.ConstructorTable.Constructors.toMutableList()
                        teamsAdapter.updateTeams(teams)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "No teams found", Toast.LENGTH_SHORT)
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
            val response = apiService.getConstructorsFromCurrentSeason(query)
            if (response.isSuccessful && response.body() != null && response.body()!!.MRData.ConstructorTable.Constructors.isNotEmpty()) {
                val filteredTeams =
                    response.body()!!.MRData.ConstructorTable.Constructors.toMutableList()
                withContext(Dispatchers.Main) {
                    teams = filteredTeams
                    teamsAdapter.updateTeams(teams)
                }
            } else {
                withContext(Dispatchers.Main) {
                    teams.clear()
                    teamsAdapter.updateTeams(teams)
                    Toast.makeText(requireContext(), "No teams found", Toast.LENGTH_SHORT).show()
                }
            }
        }
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

}