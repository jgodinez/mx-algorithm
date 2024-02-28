package com.algorithms.home

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

internal class ChallengeAdapter(
    private var selectedItem: ((item: Challenge) -> Unit)? = null
) : ListAdapter<Challenge, ChallengeViewHolder>(ChallengeDiffCallback) {

    object ChallengeDiffCallback : DiffUtil.ItemCallback<Challenge>() {
        override fun areItemsTheSame(
            oldItem: Challenge,
            newItem: Challenge
        ): Boolean {
            return oldItem.content == newItem.content
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: Challenge,
            newItem: Challenge
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        return ChallengeViewHolder.create(parent).apply {
            onSelectedItem(selectedItem)
        }
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}