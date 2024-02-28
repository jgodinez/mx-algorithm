package com.algorithms.home

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.algorithms.databinding.RowItemChallengeBinding
import com.algorithms.viewbinding.viewBinding

internal class ChallengeViewHolder(
    private val binding: RowItemChallengeBinding
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    companion object {
        fun create(parent: ViewGroup): ChallengeViewHolder {
            val binding = parent.viewBinding(RowItemChallengeBinding::inflate)
            return ChallengeViewHolder(binding)
        }
    }

    init {
        itemView.setOnClickListener(this)
    }

    fun onSelectedItem(selectedItem: ((item: Challenge) -> Unit)?) {
        this.selectedItem = selectedItem
    }

    fun bind(model: Challenge) {
        item = model
        val context = itemView.context
        with(binding) {
            textViewContent.text = item.content
            root.setBackgroundColor(ContextCompat.getColor(context, item.backgroundColor))
        }
    }

    private lateinit var item: Challenge
    private var selectedItem: ((item: Challenge) -> Unit)? = null

    override fun onClick(view: View?) {
        selectedItem?.invoke(item)
    }
}