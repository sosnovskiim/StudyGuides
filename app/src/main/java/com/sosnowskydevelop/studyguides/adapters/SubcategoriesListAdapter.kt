package com.sosnowskydevelop.studyguides.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sosnowskydevelop.studyguides.R
import com.sosnowskydevelop.studyguides.data.Subcategory
import com.sosnowskydevelop.studyguides.databinding.ListItemSubcategoryBinding
import com.sosnowskydevelop.studyguides.utilities.BUNDLE_KEY_SUBCATEGORY_ID_FROM_SUBCATEGORIES_TO_GUIDES
import com.sosnowskydevelop.studyguides.utilities.BUNDLE_KEY_SUBCATEGORY_ID_FROM_SUBCATEGORIES_TO_SUB_SUBCATEGORIES
import com.sosnowskydevelop.studyguides.utilities.REQUEST_KEY_SUBCATEGORY_ID_FROM_SUBCATEGORIES_TO_GUIDES
import com.sosnowskydevelop.studyguides.utilities.REQUEST_KEY_SUBCATEGORY_ID_FROM_SUBCATEGORIES_TO_SUB_SUBCATEGORIES
import com.sosnowskydevelop.studyguides.viewmodels.SubcategoryListItemViewModel

class SubcategoriesListAdapter(
    var subcategories: Array<Subcategory>,
    private val fragment: Fragment,
) : RecyclerView.Adapter<SubcategoriesListAdapter.ViewHolder>() {
    class ViewHolder(
        val binding: ListItemSubcategoryBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            binding = ListItemSubcategoryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel =
            SubcategoryListItemViewModel(subcategory = subcategories[position])
        if (subcategories[position].id in arrayOf(3, 4, 5)) {
            holder.binding.subcategoryName.setOnClickListener {
                fragment.setFragmentResult(
                    requestKey = REQUEST_KEY_SUBCATEGORY_ID_FROM_SUBCATEGORIES_TO_SUB_SUBCATEGORIES,
                    result = bundleOf(
                        BUNDLE_KEY_SUBCATEGORY_ID_FROM_SUBCATEGORIES_TO_SUB_SUBCATEGORIES
                                to subcategories[position].id
                    )
                )
                fragment.findNavController()
                    .navigate(R.id.action_subcategoriesFragment_to_subSubcategoriesFragment)
            }
        } else {
            holder.binding.subcategoryName.setOnClickListener {
                fragment.setFragmentResult(
                    requestKey = REQUEST_KEY_SUBCATEGORY_ID_FROM_SUBCATEGORIES_TO_GUIDES,
                    result = bundleOf(
                        BUNDLE_KEY_SUBCATEGORY_ID_FROM_SUBCATEGORIES_TO_GUIDES
                                to subcategories[position].id
                    )
                )
                if (subcategories[position].id in arrayOf(6, 7, 8)) {
                    fragment.findNavController()
                        .navigate(R.id.action_subcategoriesFragment_to_guidesFragment)
                } else {
                    fragment.findNavController()
                        .navigate(R.id.action_subSubcategoriesFragment_to_guidesFragment)
                }
            }
        }
    }

    override fun getItemCount(): Int = subcategories.size
}