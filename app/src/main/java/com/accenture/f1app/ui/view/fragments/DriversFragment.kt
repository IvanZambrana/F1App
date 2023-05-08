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
import com.accenture.f1app.data.model.driver.Driver
import com.accenture.f1app.data.network.F1ApiClient
import com.accenture.f1app.databinding.FragmentDriversBinding
import com.accenture.f1app.ui.view.detail.DriverDetailFragment
import com.accenture.f1app.ui.view.recyclerviewlist.DriverListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DriversFragment : Fragment() {

    private lateinit var binding: FragmentDriversBinding
    private var drivers = mutableListOf<Driver>()
    private lateinit var driversAdapter: DriverListAdapter
    private lateinit var rvDrivers: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_drivers, container, false)
        binding = FragmentDriversBinding.bind(rootView)


       // viewModel = ViewModelProvider(requireActivity()).get(DriverDetailsViewModel::class.java)


        initDrivers()

        binding.svDriver.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                driversAdapter.filter(newText.orEmpty())
                return false
            }
        })

        return binding.root
        //return rootView
    }

    private fun initDrivers() {
        binding.progressBarDriver.isVisible = true
        driversAdapter = DriverListAdapter(drivers){ navigateToDriverDetail(it)}
        rvDrivers = binding.driverList
        rvDrivers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvDrivers.adapter = driversAdapter

        val apiService = Core.getRetrofit().create(F1ApiClient::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getDriversFromCurrentSeason()
                if (response.isSuccessful && response.body() != null && response.body()!!.MRData.DriverTable.Drivers.isNotEmpty()) {
                    withContext(Dispatchers.Main) {
                        binding.progressBarDriver.isVisible = false
                        drivers = response.body()!!.MRData.DriverTable.Drivers.toMutableList()
                        driversAdapter.updateDrivers(drivers)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "No drivers found", Toast.LENGTH_SHORT)
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
    private fun navigateToDriverDetail(id: String){
        val bundle = Bundle()
        bundle.putString("id", id)
        val fragment = DriverDetailFragment()
        fragment.arguments = bundle
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fl_wrapper, fragment)
            .addToBackStack(id)
            .commit()
    }


    private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = Core.getRetrofit().create(F1ApiClient::class.java)
            val response = apiService.getDriversFromCurrentSeason(query)
            if (response.isSuccessful && response.body() != null && response.body()!!.MRData.DriverTable.Drivers.isNotEmpty()) {
                val filteredDrivers = response.body()!!.MRData.DriverTable.Drivers.toMutableList()
                withContext(Dispatchers.Main) {
                    drivers = filteredDrivers
                    driversAdapter.updateDrivers(drivers)
                }
            } else {
                withContext(Dispatchers.Main) {
                    drivers.clear()
                    driversAdapter.updateDrivers(drivers)
                    Toast.makeText(requireContext(), "No drivers found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}
