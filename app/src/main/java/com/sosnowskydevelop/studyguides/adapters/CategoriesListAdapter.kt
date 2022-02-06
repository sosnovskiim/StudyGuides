package com.sosnowskydevelop.studyguides.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sosnowskydevelop.studyguides.data.Category
import com.sosnowskydevelop.studyguides.databinding.ListItemCategoryBinding
import com.sosnowskydevelop.studyguides.viewmodels.CategoryListItemViewModel

class CategoriesListAdapter(
    private val categories: Array<Category>,
) : RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {
    class ViewHolder(val binding: ListItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            binding = ListItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel = CategoryListItemViewModel(category = categories[position])
    }

    override fun getItemCount(): Int = categories.size
}