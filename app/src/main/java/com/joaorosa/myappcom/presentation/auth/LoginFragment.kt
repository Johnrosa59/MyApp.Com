package com.joaorosa.myappcom.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.joaorosa.myappcom.R
import com.joaorosa.myappcom.databinding.FragmentLoginBinding
import com.joaorosa.myappcom.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            executeLoginAction()
        }
        binding.txtRegister.setOnClickListener {
            navigateToRegister()
        }
        binding.txtForgotPassword.setOnClickListener {
            navigateToForgotPassword()
        }
    }

    private fun executeLoginAction() {
        val emailText = binding.edtEmail.text.toString().trim()
        val passwordText = binding.edtPassword.text.toString().trim()
        authViewModel.loginUser(emailText, passwordText)
    }

    private fun observeViewModel() {
        authViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            renderLoadingState(isLoading)
        }
        authViewModel.loginState.observe(viewLifecycleOwner) {
            handleLoginSuccess()
        }
        authViewModel.errorMessage.observe(viewLifecycleOwner) { msg ->
            renderErrorState(msg)
        }
    }

    private fun renderLoadingState(isLoading: Boolean) {
        binding.progressLogin.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.btnLogin.isEnabled = !isLoading
    }

    private fun handleLoginSuccess() {
        renderLoadingState(false)
        Toast.makeText(requireContext(), "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun renderErrorState(errorMessage: String) {
        renderLoadingState(false)
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun navigateToRegister() {
        findNavController().navigate(R.id.action_login_to_register)
    }

    private fun navigateToForgotPassword() {
        findNavController().navigate(R.id.action_login_to_forgotPassword)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
