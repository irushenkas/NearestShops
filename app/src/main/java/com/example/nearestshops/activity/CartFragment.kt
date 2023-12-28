package com.example.nearestshops.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.nearestshops.adapter.CartAdapter
import com.example.nearestshops.adapter.OnMenuInteractionListener
import com.example.nearestshops.databinding.FragmentCartBinding
import com.example.nearestshops.dto.Menu
import dagger.hilt.android.AndroidEntryPoint
import com.example.nearestshops.viewmodel.MenuViewModel

@AndroidEntryPoint
class CartFragment : Fragment() {
    private val viewModel: MenuViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCartBinding.inflate(
            inflater,
            container,
            false
        )

        val adapter = CartAdapter(this.context, object : OnMenuInteractionListener {
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

        viewModel.getCart()

        viewModel.selectedData.observe(viewLifecycleOwner) { cart ->
            adapter.submitList(cart)
        }

        return binding.root
    }
}