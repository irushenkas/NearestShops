package com.example.nearestshops.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.nearestshops.R
import com.example.nearestshops.adapter.MenuAdapter
import com.example.nearestshops.adapter.OnMenuInteractionListener
import com.example.nearestshops.databinding.FragmentMenuBinding
import com.example.nearestshops.dto.Menu
import dagger.hilt.android.AndroidEntryPoint
import com.example.nearestshops.viewmodel.MenuViewModel

@AndroidEntryPoint
class MenuFragment : Fragment() {
    private val viewModel: MenuViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMenuBinding.inflate(
            inflater,
            container,
            false
        )

        val adapter = MenuAdapter(this.context, object : OnMenuInteractionListener {
            override fun onAdd(menu: Menu) {
                if(menu == null) {
                    return
                }
                viewModel.addToCart(menu)
            }

            override fun onRemove(menu: Menu) {
                if(menu == null) {
                    return
                }
                viewModel.removeFromCart(menu)
            }
        })

        binding.list.adapter = adapter

        val locationId = arguments?.getLong("location")
        if(locationId != null) {
            viewModel.loadMenu(locationId)
        }

        viewModel.data.observe(viewLifecycleOwner) { menu ->
            adapter.submitList(menu)
        }

        binding.cartBtn.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_cartFragment)
        }

        return binding.root
    }
}