package com.example.nearestshops.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.nearestshops.databinding.FragmentLocationsBinding
import com.example.nearestshops.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.netology.nmedia.viewmodel.LoginViewModel

@AndroidEntryPoint
class LocationsFragment : Fragment() {

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


        return binding.root
    }
}