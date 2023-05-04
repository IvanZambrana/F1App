package com.accenture.f1app.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.R
import com.accenture.f1app.core.Core
import com.accenture.f1app.data.model.team.Constructor
import com.accenture.f1app.data.network.F1ApiClient
import com.accenture.f1app.databinding.FragmentDriversBinding
import com.accenture.f1app.databinding.FragmentTeamsBinding
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

        initTeams(rootView)

        return rootView
    }

    private fun initTeams(rootView: View) {
        teamsAdapter = TeamListAdapter(teams)
        rvTeams = rootView.findViewById(R.id.teamList)
        rvTeams.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvTeams.adapter = teamsAdapter

        val apiService = Core.getRetrofit().create(F1ApiClient::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getConstructorsFromCurrentSeason()
                if (response.isSuccessful && response.body() != null && response.body()!!.MRData.ConstructorTable.Constructors.isNotEmpty()) {
                    withContext(Dispatchers.Main) {
                        teams = response.body()!!.MRData.ConstructorTable.Constructors.toMutableList()
                        teamsAdapter.teams = teams
                        teamsAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(requireContext(), "No teams found", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error loading data", Toast.LENGTH_SHORT).show()
            }
        }
    }

}