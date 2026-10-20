package com.joaorosa.myappcom.presentation.main.workouts

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.joaorosa.myappcom.databinding.FragmentWorkoutsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutsFragment : Fragment() {

    private var _binding: FragmentWorkoutsBinding? = null
    private val binding get() = _binding!!

    private val workoutsViewModel: WorkoutsViewModel by viewModels()
    private lateinit var workoutAdapter: WorkoutAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkoutsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupClickListeners()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        workoutAdapter = WorkoutAdapter(
            onDeletePlanClick = { planId ->
                workoutsViewModel.removeWorkoutPlan(planId)
            },
            onDeleteExerciseClick = { exerciseId ->
                workoutsViewModel.removeExercise(exerciseId)
            },
            onAddExerciseClick = { planId ->
                showAddExerciseDialog(planId)
            }
        )
        binding.rvWorkoutPlans.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = workoutAdapter
        }
    }

    private fun setupClickListeners() {
        binding.btnAddPlan.setOnClickListener {
            executeCreatePlanAction()
        }
        binding.btnClearAllTables.setOnClickListener {
            executeClearAllTablesAction()
        }
    }

    private fun executeCreatePlanAction() {
        val titleText = binding.edtPlanTitle.text.toString().trim()
        val categoryText = binding.edtPlanCategory.text.toString().trim()
        if (titleText.isEmpty()) {
            Toast.makeText(requireContext(), "Informe o nome do plano de treino.", Toast.LENGTH_SHORT).show()
            return
        }
        workoutsViewModel.createWorkoutPlan(titleText, categoryText)
        binding.edtPlanTitle.text?.clear()
        binding.edtPlanCategory.text?.clear()
        Toast.makeText(requireContext(), "Plano de treino cadastrado em Room DB!", Toast.LENGTH_SHORT).show()
    }

    private fun executeClearAllTablesAction() {
        workoutsViewModel.clearAllTables()
        Toast.makeText(requireContext(), "Todas as tabelas foram limpas!", Toast.LENGTH_SHORT).show()
    }

    private fun showAddExerciseDialog(planId: Long) {
        val context = requireContext()
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 40, 50, 10)
        }

        val edtName = EditText(context).apply { hint = "Nome do Exercício (Ex: Supino)" }
        val edtSets = EditText(context).apply {
            hint = "Séries (Ex: 4)"
            inputType = android.text.InputType.TYPE_CLASS_NUMBER
        }
        val edtReps = EditText(context).apply {
            hint = "Repetições (Ex: 10)"
            inputType = android.text.InputType.TYPE_CLASS_NUMBER
        }
        val edtWeight = EditText(context).apply {
            hint = "Carga em kg (Ex: 50.0)"
            inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL
        }

        layout.addView(edtName)
        layout.addView(edtSets)
        layout.addView(edtReps)
        layout.addView(edtWeight)

        AlertDialog.Builder(context)
            .setTitle("Cadastrar Exercício")
            .setMessage("O exercício usará a Chave Estrangeira planId: $planId")
            .setView(layout)
            .setPositiveButton("Adicionar") { _, _ ->
                val name = edtName.text.toString().trim()
                val sets = edtSets.text.toString().toIntOrNull() ?: 3
                val reps = edtReps.text.toString().toIntOrNull() ?: 10
                val weight = edtWeight.text.toString().toDoubleOrNull() ?: 0.0
                workoutsViewModel.addExerciseToPlan(planId, name, sets, reps, weight)
                Toast.makeText(context, "Exercício adicionado!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun observeViewModel() {
        workoutsViewModel.workoutPlansState.observe(viewLifecycleOwner) { plans ->
            workoutAdapter.submitList(plans)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
