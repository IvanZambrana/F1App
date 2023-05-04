package com.accenture.f1app.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.R
import com.accenture.f1app.core.Core
import com.accenture.f1app.data.model.driver.Driver
import com.accenture.f1app.data.network.F1ApiClient
import com.accenture.f1app.databinding.FragmentDriversBinding
import com.accenture.f1app.ui.view.recyclerviewlist.DriverListAdapter
import com.accenture.f1app.ui.view.recyclerviewshome.DriversAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.create


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

        initDrivers(rootView)

        /* binding.svDriver.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
             override fun onQueryTextSubmit(query: String?): Boolean {
                 searchByName(query.orEmpty())
                 return false
             }

             override fun onQueryTextChange(newText: String?): Boolean {
                 return false
             }
         })*/

        return rootView
    }

    private fun initDrivers(rootView: View) {
        driversAdapter = DriverListAdapter(drivers)
        rvDrivers = rootView.findViewById(R.id.driverList)
        rvDrivers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
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
}
/*
private fun searchByName(query: String) {
    CoroutineScope(Dispatchers.IO).launch {
        val call = Core.getRetrofit().create(F1ApiClient::class.java).getDriversFromCurrentSeason()
        val driver = call.body()
        /*{
            if(call.isSuccessful){
                //Show RecyclerView
            }else{
                //Show error
            }
        }*/

    }
}*/


