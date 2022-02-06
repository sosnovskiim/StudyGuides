package com.sosnowskydevelop.studyguides.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sosnowskydevelop.studyguides.R
import com.sosnowskydevelop.studyguides.data.Category
import com.sosnowskydevelop.studyguides.databinding.ListItemCategoryBinding
import com.sosnowskydevelop.studyguides.utilities.BUNDLE_KEY_CATEGORY_ID_FROM_CATEGORIES_TO_SUBCATEGORIES
import com.sosnowskydevelop.studyguides.utilities.REQUEST_KEY_CATEGORY_ID_FROM_CATEGORIES_TO_SUBCATEGORIES
import com.sosnowskydevelop.studyguides.viewmodels.CategoryListItemViewModel

class CategoriesListAdapter(
    private val categories: Array<Category>,
    private val fragment: Fragment,
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
        holder.binding.categoryName.setOnClickListener {
            fragment.setFragmentResult(
                requestKey = REQUEST_KEY_CATEGORY_ID_FROM_CATEGORIES_TO_SUBCATEGORIES,
                result = bundleOf(
                    BUNDLE_KEY_CATEGORY_ID_FROM_CATEGORIES_TO_SUBCATEGORIES
                            to categories[position]._id
                )
            )
            fragment.findNavController()
                .navigate(R.id.action_categoriesFragment_to_subcategoriesFragment)
        }
    }

    override fun getItemCount(): Int = categories.size
}