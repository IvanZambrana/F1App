package com.accenture.f1app.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.accenture.f1app.R
import com.accenture.f1app.core.Core
import com.accenture.f1app.data.network.F1ApiClient
import com.accenture.f1app.databinding.FragmentDriversBinding
import com.accenture.f1app.ui.view.recyclerviewshome.DriversAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.create


class DriversFragment : Fragment() {

    private lateinit var binding: FragmentDriversBinding
    private lateinit var adapter: DriversAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drivers, container, false)



        binding.svDriver.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
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
    }

}
