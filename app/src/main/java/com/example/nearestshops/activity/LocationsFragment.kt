package com.example.nearestshops.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.nearestshops.adapter.LocationsAdapter
import com.example.nearestshops.adapter.OnLocationsInteractionListener
import com.example.nearestshops.databinding.FragmentLocationsBinding
import com.example.nearestshops.dto.Location
import dagger.hilt.android.AndroidEntryPoint
import com.example.nearestshops.viewmodel.LocationsViewModel

@AndroidEntryPoint
class LocationsFragment : Fragment() {
    private val viewModel: LocationsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLocationsBinding.inflate(
            inflater,
            container,
            false
        )

        val adapter = LocationsAdapter(this.context, object : OnLocationsInteractionListener {
            override fun onSelect(location: Location) {
                if(location == null) {
                    return
                }
                val bundle = Bundle()
                bundle.putLong("location", location.id)
                //findNavController().navigate(R.id.action_authorJobsFragment_to_editAuthorJobFragment, bundle)
            }
        })

        binding.list.adapter = adapter

        viewModel.loadLocations()

        viewModel.data.observe(viewLifecycleOwner) { locations ->
            adapter.submitList(locations)
        }

        return binding.root
    }
}