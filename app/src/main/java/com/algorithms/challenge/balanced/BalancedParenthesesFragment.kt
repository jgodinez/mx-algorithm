package com.algorithms.challenge.balanced

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.algorithms.R
import com.algorithms.databinding.FragmentBalancedParenthesesBinding
import com.algorithms.home.onBackPressed
import com.algorithms.viewbinding.viewBinding

internal class BalancedParenthesesFragment : Fragment(R.layout.fragment_balanced_parentheses) {

    private val binding: FragmentBalancedParenthesesBinding by viewBinding(
        FragmentBalancedParenthesesBinding::bind
    )

    private val viewModel: BalancedParenthesesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareUI()
        observeState()
    }

    private fun prepareUI() {
        with(binding) {
            editTextInput.setText("(Hello (,) world (!))")
            textViewOutput.text = getString(R.string.balanced_output_hint, "")
            topAppBar.setNavigationOnClickListener {
                onBackPressed()
            }
            buttonCheckBalance.setOnClickListener {
                val input = editTextInput.text?.toString().orEmpty()
                viewModel.checkBalanced(input)
            }
        }
    }

    private fun observeState() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                BalancedUiState.Loading -> loadingState(true)
                is BalancedUiState.Success -> successState(state.outputValue)
            }
        }
    }

    private fun loadingState(loading: Boolean) {
        binding.progressIndicator.isVisible = loading
    }

    private fun successState(outputValue: String) {
        binding.textViewOutput.text = getString(R.string.balanced_output_hint, outputValue)
        loadingState(false)
    }
}