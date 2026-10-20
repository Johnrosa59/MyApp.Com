package com.joaorosa.myappcom.presentation.main.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.joaorosa.myappcom.databinding.FragmentProfileBinding
import com.joaorosa.myappcom.domain.model.User
import com.joaorosa.myappcom.presentation.auth.AuthActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val profileViewModel: ProfileViewModel by viewModels()
    private var currentUserId: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeViewModel()
        profileViewModel.loadProfile()
    }

    private fun setupClickListeners() {
        binding.btnSaveProfile.setOnClickListener {
            executeSaveProfileAction()
        }
        binding.btnLogout.setOnClickListener {
            executeLogoutAction()
        }
    }

    private fun executeSaveProfileAction() {
        val updatedUser = User(
            id = currentUserId,
            name = binding.edtProfileName.text.toString().trim(),
            weightKg = binding.edtProfileWeight.text.toString().toDoubleOrNull() ?: 0.0,
            heightCm = binding.edtProfileHeight.text.toString().toDoubleOrNull() ?: 0.0,
            dailyCalorieGoal = binding.edtProfileGoal.text.toString().toIntOrNull() ?: 2000
        )
        profileViewModel.updateProfile(updatedUser)
    }

    private fun executeLogoutAction() {
        profileViewModel.logout()
        val intent = Intent(requireContext(), AuthActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun observeViewModel() {
        profileViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            renderLoadingState(isLoading)
        }

        profileViewModel.userProfileState.observe(viewLifecycleOwner) { user ->
            renderProfileFields(user)
        }

        profileViewModel.saveSuccess.observe(viewLifecycleOwner) {
            handleSaveSuccess()
        }

        profileViewModel.errorMessage.observe(viewLifecycleOwner) { msg ->
            renderErrorState(msg)
        }
    }

    private fun renderLoadingState(isLoading: Boolean) {
        binding.progressProfile.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.btnSaveProfile.isEnabled = !isLoading
    }

    private fun renderProfileFields(user: User) {
        renderLoadingState(false)
        currentUserId = user.id
        binding.edtProfileName.setText(user.name)
        binding.edtProfileWeight.setText(user.weightKg.toString())
        binding.edtProfileHeight.setText(user.heightCm.toString())
        binding.edtProfileGoal.setText(user.dailyCalorieGoal.toString())
    }

    private fun handleSaveSuccess() {
        renderLoadingState(false)
        Toast.makeText(requireContext(), "Perfil salvo no Firestore com sucesso!", Toast.LENGTH_SHORT).show()
    }

    private fun renderErrorState(errorMessage: String) {
        renderLoadingState(false)
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
