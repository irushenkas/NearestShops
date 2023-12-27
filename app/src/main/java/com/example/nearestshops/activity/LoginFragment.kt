package com.example.nearestshops.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.nearestshops.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.netology.nmedia.viewmodel.LoginViewModel

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(
            inflater,
            container,
            false
        )
        binding.loginBtn.setOnClickListener{
            viewModel.signIn(binding.loginField.text.toString(), binding.passwordField.text.toString())
        }

        return binding.root
    }
}