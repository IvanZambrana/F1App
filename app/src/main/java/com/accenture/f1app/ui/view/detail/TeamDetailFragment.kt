package com.accenture.f1app.ui.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.accenture.f1app.R
import com.accenture.f1app.core.Core
import com.accenture.f1app.data.network.F1ApiClient
import com.accenture.f1app.databinding.FragmentTeamDetailBinding
import com.accenture.f1app.databinding.ItemDriverlistBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TeamDetailFragment : Fragment() {

    private lateinit var binding: FragmentTeamDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTeamDetailBinding.inflate(inflater, container, false)


        //Get constructor Id
        val constructorId = arguments?.getString("id")
        if (constructorId == null){
            Toast.makeText(requireContext(), "Constructor ID not found", Toast.LENGTH_SHORT).show()
            return null
        }

        //Coroutine
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = Core.getRetrofit().create(F1ApiClient::class.java)
            val response = apiService.getConstructorById(constructorId)

            withContext(Dispatchers.Main) {
                val constructor = response.body()?.MRData?.ConstructorTable?.Constructors?.first()
                binding.tvTeamNameDetail.text = constructor?.name
                binding.tvTeamNationalityDetail.text = constructor?.nationality
                val teamId = constructor?.constructorId
                val teamImageResource = context?.resources?.getIdentifier(
                    teamId,
                    "drawable",
                    requireContext().packageName
                )
                if (teamImageResource != null) {
                    binding.ivTeamDetail.setImageResource(teamImageResource)
                }
                val teamColor = when (constructorId) {
                    "williams" -> R.color.williams
                    "aston_martin" -> R.color.astonmartin
                    "alfa" -> R.color.alfaromeo
                    "alphatauri" -> R.color.alphatauri
                    "alpine" -> R.color.alpine
                    "mercedes" -> R.color.mercedes
                    "haas" -> R.color.haas
                    "mclaren" -> R.color.mclaren
                    "red_bull" -> R.color.redbull
                    "ferrari" -> R.color.ferrari
                    else -> R.color.white
                }
                binding.teamDividerdetail.setBackgroundResource(teamColor)
            }

        }









        return binding.root
    }


}