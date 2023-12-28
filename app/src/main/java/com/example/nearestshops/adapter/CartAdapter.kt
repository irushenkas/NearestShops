package com.example.nearestshops.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nearestshops.databinding.CardCartBinding
import com.example.nearestshops.dto.Menu

class CartAdapter(private val context: Context?, private val onInteractionListener: OnMenuInteractionListener
) : ListAdapter<Menu, CartViewHolder>(MenuDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CardCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(context, binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val menu = getItem(position)
        holder.bind(menu)
    }
}

class CartViewHolder(
    private val context: Context?,
    private val binding: CardCartBinding,
    private val onInteractionListener: OnMenuInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(menu: Menu) {
        binding.apply {
            name.text = menu.name
            if(menu.price != null) {
                price.text = menu.price.toString()
            }
            count.text = menu.count.toString()

            add.setOnClickListener {
                menu.count++
                count.text = menu.count.toString()

                onInteractionListener.onAdd(menu)
            }

            remove.setOnClickListener {
                if(menu.count == 0) {
                    return@setOnClickListener
                }
                menu.count--
                count.text = menu.count.toString()

                onInteractionListener.onRemove(menu)
            }
        }
    }
}
