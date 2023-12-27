package com.example.nearestshops.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nearestshops.databinding.CardLocationBinding
import com.example.nearestshops.dto.Location

interface OnLocationsInteractionListener {
    fun onSelect(location: Location) {}
}

class LocationsAdapter(private val context: Context?, private val onInteractionListener: OnLocationsInteractionListener
) : ListAdapter<Location, LocationsViewHolder>(LocationsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        val binding = CardLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationsViewHolder(context, binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        val location = getItem(position)
        holder.bind(location)
    }
}

class LocationsViewHolder(
    private val context: Context?,
    private val binding: CardLocationBinding,
    private val onInteractionListener: OnLocationsInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(location: Location) {
        binding.apply {
            name.text = location.name

            card.setOnClickListener {
                onInteractionListener.onSelect(location)
            }
        }
    }
}

class LocationsDiffCallback : DiffUtil.ItemCallback<Location>() {
    override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
        return oldItem == newItem
    }
}
