package com.sosnowskydevelop.studyguides.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sosnowskydevelop.studyguides.data.Guide
import com.sosnowskydevelop.studyguides.databinding.ListItemGuideBinding
import com.sosnowskydevelop.studyguides.viewmodels.GuideListItemViewModel

class GuidesListAdapter(
    var guides: Array<Guide>,
) : RecyclerView.Adapter<GuidesListAdapter.ViewHolder>() {
    class ViewHolder(
        val binding: ListItemGuideBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            binding = ListItemGuideBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel =
            GuideListItemViewModel(guide = guides[position])
    }

    override fun getItemCount(): Int = guides.size
}