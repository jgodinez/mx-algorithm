package com.algorithms.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.algorithms.BuildConfig
import com.algorithms.R
import com.algorithms.databinding.FragmentHomeBinding
import com.algorithms.viewbinding.viewBinding

internal class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)

    private val challengeAdapter: ChallengeAdapter by lazy {
        ChallengeAdapter(selectedItem = ::selectedChallenge)
    }

    private val challengeItems: List<Challenge> by lazy {
        listOf(
            Challenge(
                "Alphanumeric sort",
                R.color.turquoise,
                R.id.action_homeFragment_to_alphanumericSortFragment
            ),
            Challenge("Next challenge", R.color.alizarin),
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareUI()
    }

    private fun prepareUI() {
        with(binding) {
            recyclerViewChallengeItems.apply {
                adapter = challengeAdapter
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                    )
                )
            }

            textViewVersion.text = getString(R.string.home_app_version, BuildConfig.VERSION_NAME)
        }
        challengeAdapter.submitList(challengeItems)
    }

    private fun selectedChallenge(challenge: Challenge) {
        challenge.launchResource?.let { navigate(it) }
    }
}