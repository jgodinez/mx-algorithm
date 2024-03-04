package com.algorithms.challenge.sort

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.algorithms.R
import com.algorithms.databinding.FragmentAlphanumericSortBinding
import com.algorithms.home.onBackPressed
import com.algorithms.viewbinding.viewBinding

internal class AlphanumericSortFragment : Fragment(R.layout.fragment_alphanumeric_sort) {

    private val binding: FragmentAlphanumericSortBinding by viewBinding(
        FragmentAlphanumericSortBinding::bind
    )

    private val viewModel: AlphanumericSortViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareUI()
        observeState()
    }

    private fun prepareUI() {
        with(binding) {
            editTextInput.setText("P@rAnar1cu+iRImïcü4r0")
            topAppBar.setNavigationOnClickListener {
                onBackPressed()
            }
            buttonSort.setOnClickListener {
                val input = editTextInput.text?.toString().orEmpty()
                val trimLineBreaks = checkBoxTrimLineBreak.isChecked
                viewModel.sort(input, trimLineBreaks)
            }
        }
    }

    private fun observeState() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                SortUiState.Loading -> loadingState(true)
                is SortUiState.Success -> successState(state.sortedString)
            }
        }
    }

    private fun loadingState(loading: Boolean) {
        binding.progressIndicator.isVisible = loading
    }

    private fun successState(sortedString: String) {
        binding.editTextOutput.setText(sortedString)
        loadingState(false)
    }
}

