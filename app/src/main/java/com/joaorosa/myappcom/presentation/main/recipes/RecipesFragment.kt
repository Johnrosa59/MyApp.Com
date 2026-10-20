package com.joaorosa.myappcom.presentation.main.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.joaorosa.myappcom.databinding.FragmentRecipesBinding
import com.joaorosa.myappcom.domain.model.Recipe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private val recipesViewModel: RecipesViewModel by viewModels()
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        recipesViewModel.loadRecipes()
    }

    private fun setupRecyclerView() {
        recipeAdapter = RecipeAdapter()
        binding.rvRecipes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipeAdapter
        }
    }

    private fun observeViewModel() {
        recipesViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            renderLoadingState(isLoading)
        }
        recipesViewModel.recipesState.observe(viewLifecycleOwner) { recipes ->
            renderRecipesList(recipes)
        }
        recipesViewModel.errorMessage.observe(viewLifecycleOwner) { msg ->
            renderErrorState(msg)
        }
    }

    private fun renderLoadingState(isLoading: Boolean) {
        binding.progressRecipes.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun renderRecipesList(recipes: List<Recipe>) {
        renderLoadingState(false)
        recipeAdapter.submitList(recipes)
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
