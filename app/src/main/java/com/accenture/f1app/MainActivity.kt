package com.accenture.f1app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.accenture.f1app.databinding.ActivityMainBinding
import com.accenture.f1app.fragments.*
import com.accenture.f1app.recyclerviewshome.DriversAdapter

class MainActivity : AppCompatActivity() {



    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Set home fragment as default
        makeCurrentFragment(HomeFragment())



        //Replace Fragment with the bottom nav bar
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.itemHome -> makeCurrentFragment(HomeFragment())
                R.id.itemDrivers -> makeCurrentFragment(DriversFragment())
                R.id.itemCircuits -> makeCurrentFragment(CircuitsFragment())
                R.id.itemTeams -> makeCurrentFragment(TeamsFragment())
                R.id.itemFav -> makeCurrentFragment(FavouritesFragment())
            }
            true
        }

    }




    private fun makeCurrentFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_wrapper, fragment)
        fragmentTransaction.commit()
    }
}