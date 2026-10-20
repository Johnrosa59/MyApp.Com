package com.joaorosa.myappcom.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.joaorosa.myappcom.databinding.FragmentForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {
        binding.btnResetPassword.setOnClickListener {
            executeResetPasswordAction()
        }
    }

    private fun executeResetPasswordAction() {
        val emailText = binding.edtEmailForgot.text.toString().trim()
        authViewModel.sendPasswordReset(emailText)
    }

    private fun observeViewModel() {
        authViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            renderLoadingState(isLoading)
        }
        authViewModel.resetPasswordState.observe(viewLifecycleOwner) {
            handleResetSuccess()
        }
        authViewModel.errorMessage.observe(viewLifecycleOwner) { msg ->
            renderErrorState(msg)
        }
    }

    private fun renderLoadingState(isLoading: Boolean) {
        binding.progressForgot.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.btnResetPassword.isEnabled = !isLoading
    }

    private fun handleResetSuccess() {
        renderLoadingState(false)
        Toast.makeText(
            requireContext(),
            "E-mail de redefinição enviado! Verifique sua caixa de entrada.",
            Toast.LENGTH_LONG
        ).show()
        findNavController().popBackStack()
    }

    private fun renderErrorState(errorMessage: String) {
        renderLoadingState(false)
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
