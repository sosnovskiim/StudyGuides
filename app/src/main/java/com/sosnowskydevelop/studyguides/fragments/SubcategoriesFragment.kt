package com.sosnowskydevelop.studyguides.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sosnowskydevelop.studyguides.adapters.SubcategoriesListAdapter
import com.sosnowskydevelop.studyguides.databinding.FragmentSubcategoriesBinding
import com.sosnowskydevelop.studyguides.utilities.BUNDLE_KEY_CATEGORY_ID_FROM_CATEGORIES_TO_SUBCATEGORIES
import com.sosnowskydevelop.studyguides.utilities.InjectorUtils
import com.sosnowskydevelop.studyguides.utilities.REQUEST_KEY_CATEGORY_ID_FROM_CATEGORIES_TO_SUBCATEGORIES
import com.sosnowskydevelop.studyguides.viewmodels.SubcategoriesViewModel

class SubcategoriesFragment : Fragment() {
    private lateinit var binding: FragmentSubcategoriesBinding

    private val viewModel: SubcategoriesViewModel by viewModels {
        InjectorUtils.provideSubcategoriesViewModelFactory(context = requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubcategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        setActionBarTitle(actionBar = actionBar) // Return to the fragment.
        actionBar?.show()

        val layoutManager = LinearLayoutManager(requireContext())
        binding.subcategories.layoutManager = layoutManager

        val adapter = SubcategoriesListAdapter(
            subcategories = viewModel.subcategories,
            fragment = this,
        )
        binding.subcategories.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(
            binding.subcategories.context,
            layoutManager.orientation
        )
        binding.subcategories.addItemDecoration(dividerItemDecoration)

        setFragmentResultListener(
            requestKey = REQUEST_KEY_CATEGORY_ID_FROM_CATEGORIES_TO_SUBCATEGORIES
        ) { _, bundle ->
            viewModel.initData(
                categoryId = bundle.getInt(
                    BUNDLE_KEY_CATEGORY_ID_FROM_CATEGORIES_TO_SUBCATEGORIES
                )
            )

            setActionBarTitle(actionBar = actionBar) // First opening of the fragment.

            adapter.subcategories = viewModel.subcategories
        }
    }

    private fun setActionBarTitle(actionBar: ActionBar?) {
        actionBar?.title = viewModel.categoryName
    }
}