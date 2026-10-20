package com.joaorosa.myappcom.presentation.main.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaorosa.myappcom.domain.model.Recipe
import com.joaorosa.myappcom.domain.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    private val _recipesState = MutableLiveData<List<Recipe>>()
    val recipesState: LiveData<List<Recipe>> get() = _recipesState

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun loadRecipes() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val list = getRecipesUseCase.executeFetchRecipes()
                _recipesState.value = list
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "Erro ao carregar receitas."
            } finally {
                _isLoading.value = false
            }
        }
    }
}
