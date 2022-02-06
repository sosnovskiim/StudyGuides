package com.sosnowskydevelop.studyguides.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sosnowskydevelop.studyguides.data.Subcategory
import com.sosnowskydevelop.studyguides.databinding.ListItemSubcategoryBinding
import com.sosnowskydevelop.studyguides.viewmodels.SubcategoryListItemViewModel

class SubcategoriesListAdapter(
    var subcategories: Array<Subcategory>,
) : RecyclerView.Adapter<SubcategoriesListAdapter.ViewHolder>() {
    class ViewHolder(val binding: ListItemSubcategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            binding = ListItemSubcategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel =
            SubcategoryListItemViewModel(subcategory = subcategories[position])
    }

    override fun getItemCount(): Int = subcategories.size
}