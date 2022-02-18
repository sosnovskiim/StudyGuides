package com.sosnowskydevelop.studyguides.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sosnowskydevelop.studyguides.adapters.CategoriesListAdapter
import com.sosnowskydevelop.studyguides.databinding.FragmentCategoriesBinding
import com.sosnowskydevelop.studyguides.utilities.InjectorUtils
import com.sosnowskydevelop.studyguides.viewmodels.CategoriesViewModel

class CategoriesFragment : Fragment() {
    private val viewModel: CategoriesViewModel by viewModels {
        InjectorUtils.provideCategoriesViewModelFactory(context = requireContext())
    }

    private lateinit var binding: FragmentCategoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.hide()

        val layoutManager = LinearLayoutManager(requireContext())
        binding.categories.layoutManager = layoutManager

        binding.categories.adapter = CategoriesListAdapter(
            categories = viewModel.categories,
            fragment = this,
        )

        val dividerItemDecoration = DividerItemDecoration(
            binding.categories.context,
            layoutManager.orientation
        )
        binding.categories.addItemDecoration(dividerItemDecoration)
    }
}