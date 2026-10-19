package com.joaorosa.myappcom.presentation.main.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joaorosa.myappcom.databinding.ItemRecipeBinding
import com.joaorosa.myappcom.domain.model.Recipe

class RecipeAdapter : ListAdapter<Recipe, RecipeAdapter.RecipeViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipeItem = getItem(position)
        holder.bind(recipeItem)
    }

    class RecipeViewHolder(
        private val binding: ItemRecipeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Recipe) {
            binding.txtRecipeTitle.text = item.title
            binding.txtRecipeCalories.text = "Calorias: ${item.caloriesPerServing} kcal"
            binding.txtRecipePrepTime.text = "Preparo: ${item.prepTimeMinutes} min"
            binding.txtRecipeDifficulty.text = "Dificuldade: ${item.difficulty}"
            binding.txtRecipeIngredients.text = "Ingredientes: ${item.ingredients.joinToString(", ")}"
        }
    }

    private class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }
}
