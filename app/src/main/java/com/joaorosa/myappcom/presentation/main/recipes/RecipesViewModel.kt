package com.joaorosa.myappcom.presentation.main.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joaorosa.myappcom.domain.model.Recipe
import com.joaorosa.myappcom.domain.model.Resource
import com.joaorosa.myappcom.domain.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    private val _recipesState: MutableLiveData<Resource<List<Recipe>>> = MutableLiveData()
    val recipesState: LiveData<Resource<List<Recipe>>> get() = _recipesState

    fun loadRecipes() {
        _recipesState.value = Resource.Loading
        viewModelScope.launch {
            val result = getRecipesUseCase.executeFetchRecipes()
            _recipesState.postValue(result)
        }
    }
}
