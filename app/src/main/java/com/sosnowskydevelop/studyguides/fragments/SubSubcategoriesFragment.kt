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
import com.sosnowskydevelop.studyguides.databinding.FragmentSubSubcategoriesBinding
import com.sosnowskydevelop.studyguides.utilities.*
import com.sosnowskydevelop.studyguides.viewmodels.SubSubcategoriesViewModel

class SubSubcategoriesFragment : Fragment() {
    private val viewModel: SubSubcategoriesViewModel by viewModels {
        InjectorUtils.provideSubSubcategoriesViewModelFactory(context = requireContext())
    }

    private lateinit var binding: FragmentSubSubcategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubSubcategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        setActionBarTitle(actionBar = actionBar) // Return to the fragment.

        val layoutManager = LinearLayoutManager(requireContext())
        binding.subSubcategories.layoutManager = layoutManager

        val adapter = SubcategoriesListAdapter(
            subcategories = viewModel.subcategories,
            fragment = this,
        )
        binding.subSubcategories.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(
            binding.subSubcategories.context,
            layoutManager.orientation
        )
        binding.subSubcategories.addItemDecoration(dividerItemDecoration)

        setFragmentResultListener(
            requestKey = REQUEST_KEY_SUBCATEGORY_ID_FROM_SUBCATEGORIES_TO_SUB_SUBCATEGORIES
        ) { _, bundle ->
            viewModel.initData(
                subcategoryId = bundle.getInt(
                    BUNDLE_KEY_SUBCATEGORY_ID_FROM_SUBCATEGORIES_TO_SUB_SUBCATEGORIES
                )
            )

            setActionBarTitle(actionBar = actionBar) // First opening of the fragment.

            adapter.subcategories = viewModel.subcategories
        }
    }

    private fun setActionBarTitle(actionBar: ActionBar?) {
        actionBar?.title = viewModel.subcategoryName
    }
}