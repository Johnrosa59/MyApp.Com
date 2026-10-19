package com.joaorosa.myappcom.presentation.main.workouts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.joaorosa.myappcom.databinding.ItemExerciseBinding
import com.joaorosa.myappcom.databinding.ItemWorkoutPlanBinding
import com.joaorosa.myappcom.domain.model.WorkoutPlan

class WorkoutAdapter(
    private val onDeletePlanClick: (planId: Long) -> Unit,
    private val onDeleteExerciseClick: (exerciseId: Long) -> Unit,
    private val onAddExerciseClick: (planId: Long) -> Unit
) : ListAdapter<WorkoutPlan, WorkoutAdapter.WorkoutViewHolder>(WorkoutDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val binding = ItemWorkoutPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkoutViewHolder(binding, onDeletePlanClick, onDeleteExerciseClick, onAddExerciseClick)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val planItem = getItem(position)
        holder.bind(planItem)
    }

    class WorkoutViewHolder(
        private val binding: ItemWorkoutPlanBinding,
        private val onDeletePlanClick: (planId: Long) -> Unit,
        private val onDeleteExerciseClick: (exerciseId: Long) -> Unit,
        private val onAddExerciseClick: (planId: Long) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WorkoutPlan) {
            binding.txtPlanTitleItem.text = item.title
            binding.txtPlanCategoryItem.text = "Categoria: ${item.category}"

            binding.btnDeletePlanItem.setOnClickListener {
                onDeletePlanClick(item.planId)
            }

            binding.btnAddExerciseItem.setOnClickListener {
                onAddExerciseClick(item.planId)
            }

            binding.layoutExercisesContainer.removeAllViews()
            val inflater = LayoutInflater.from(binding.root.context)
            item.exercises.forEach { exerciseItem ->
                val exerciseBinding = ItemExerciseBinding.inflate(inflater, binding.layoutExercisesContainer, false)
                exerciseBinding.txtExerciseName.text = exerciseItem.name
                exerciseBinding.txtExerciseDetails.text =
                    "${exerciseItem.sets} séries x ${exerciseItem.reps} reps - ${exerciseItem.weightKg} kg"
                exerciseBinding.btnDeleteExerciseItem.setOnClickListener {
                    onDeleteExerciseClick(exerciseItem.exerciseId)
                }
                binding.layoutExercisesContainer.addView(exerciseBinding.root)
            }
        }
    }

    private class WorkoutDiffCallback : DiffUtil.ItemCallback<WorkoutPlan>() {
        override fun areItemsTheSame(oldItem: WorkoutPlan, newItem: WorkoutPlan): Boolean {
            return oldItem.planId == newItem.planId
        }

        override fun areContentsTheSame(oldItem: WorkoutPlan, newItem: WorkoutPlan): Boolean {
            return oldItem == newItem
        }
    }
}
