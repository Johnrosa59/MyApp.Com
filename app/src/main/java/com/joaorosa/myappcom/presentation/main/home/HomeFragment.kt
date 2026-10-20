package com.joaorosa.myappcom.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.joaorosa.myappcom.databinding.FragmentHomeBinding
import com.joaorosa.myappcom.domain.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        homeViewModel.fetchProfileData()
    }

    private fun observeViewModel() {
        homeViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            renderLoadingState(isLoading)
        }
        homeViewModel.userProfileState.observe(viewLifecycleOwner) { user ->
            renderUserData(user)
        }
        homeViewModel.errorMessage.observe(viewLifecycleOwner) { msg ->
            renderErrorState(msg)
        }
    }

    private fun renderLoadingState(isLoading: Boolean) {
        binding.progressHome.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun renderUserData(user: User) {
        renderLoadingState(false)
        binding.txtProfileName.text = "Usuário: ${user.name}"
        binding.txtProfileEmail.text = "Email: ${user.email}"
        binding.txtCalorieGoal.text = "Meta Calórica Diária: ${user.dailyCalorieGoal} kcal"
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
