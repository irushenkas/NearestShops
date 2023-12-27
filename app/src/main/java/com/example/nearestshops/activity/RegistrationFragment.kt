package com.example.nearestshops.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.nearestshops.R
import com.example.nearestshops.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.netology.nmedia.viewmodel.LoginViewModel

@AndroidEntryPoint
class RegistrationFragment : Fragment() {
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentRegistrationBinding.inflate(
            inflater,
            container,
            false
        )
        binding.registerBtn.setOnClickListener{
            viewModel.signUp(binding.emailField.text.toString(), binding.passwordField.text.toString(),
                binding.passwordConfirmField.text.toString())
        }

        viewModel.token.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_registrationFragment_to_locationsFragment)
        })

        return binding.root
    }
}