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
import com.sosnowskydevelop.studyguides.adapters.GuidesListAdapter
import com.sosnowskydevelop.studyguides.databinding.FragmentGuidesBinding
import com.sosnowskydevelop.studyguides.utilities.*
import com.sosnowskydevelop.studyguides.viewmodels.GuidesViewModel

class GuidesFragment : Fragment() {
    private val viewModel: GuidesViewModel by viewModels {
        InjectorUtils.provideGuidesViewModelFactory(context = requireContext())
    }

    private lateinit var binding: FragmentGuidesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuidesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        setActionBarTitle(actionBar = actionBar) // Return to the fragment.

        val layoutManager = LinearLayoutManager(requireContext())
        binding.guides.layoutManager = layoutManager

        val adapter = GuidesListAdapter(
            guides = viewModel.guides,
            fragment = this,
        )
        binding.guides.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(
            binding.guides.context,
            layoutManager.orientation
        )
        binding.guides.addItemDecoration(dividerItemDecoration)

        setFragmentResultListener(
            requestKey = REQUEST_KEY_SUBCATEGORY_ID_FROM_SUBCATEGORIES_TO_GUIDES
        ) { _, bundle ->
            viewModel.initData(
                subcategoryId = bundle.getInt(
                    BUNDLE_KEY_SUBCATEGORY_ID_FROM_SUBCATEGORIES_TO_GUIDES
                )
            )

            setActionBarTitle(actionBar = actionBar) // First opening of the fragment.

            adapter.guides = viewModel.guides
        }
    }

    private fun setActionBarTitle(actionBar: ActionBar?) {
        actionBar?.title = viewModel.subcategoryName
    }
}