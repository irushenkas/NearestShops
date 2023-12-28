package com.example.nearestshops.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nearestshops.databinding.CardMenuBinding
import com.example.nearestshops.dto.Menu
import com.squareup.picasso.Picasso

interface OnMenuInteractionListener {
    fun onAdd(menu: Menu) {}
    fun onRemove(menu: Menu) {}
}

class MenuAdapter(private val context: Context?, private val onInteractionListener: OnMenuInteractionListener
) : ListAdapter<Menu, MenuViewHolder>(MenuDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = CardMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(context, binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = getItem(position)
        holder.bind(menu)
    }
}

class MenuViewHolder(
    private val context: Context?,
    private val binding: CardMenuBinding,
    private val onInteractionListener: OnMenuInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(menu: Menu) {
        binding.apply {
            name.text = menu.name
            if(menu.price != null) {
                price.text = menu.price.toString()
            }
            if(menu.imageURL != null) {
                Picasso.get().load(menu.imageURL).into(image)
            }

//            card.setOnClickListener {
//                onInteractionListener.onSelect(menu)
//            }
        }
    }
}

class MenuDiffCallback : DiffUtil.ItemCallback<Menu>() {
    override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean {
        return oldItem == newItem
    }
}
