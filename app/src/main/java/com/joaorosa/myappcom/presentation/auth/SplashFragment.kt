package com.joaorosa.myappcom.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.joaorosa.myappcom.R
import com.joaorosa.myappcom.databinding.FragmentSplashBinding
import com.joaorosa.myappcom.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startSplashTimer()
    }

    private fun startSplashTimer() {
        Handler(Looper.getMainLooper()).postDelayed({
            checkAuthAndNavigate()
        }, 1500L)
    }

    private fun checkAuthAndNavigate() {
        if (!isAdded) return
        if (authViewModel.isUserLoggedIn()) {
            navigateToMainScreen()
        } else {
            navigateToLoginScreen()
        }
    }

    private fun navigateToMainScreen() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun navigateToLoginScreen() {
        findNavController().navigate(R.id.action_splash_to_login)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
