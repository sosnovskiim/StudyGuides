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
import com.sosnowskydevelop.studyguides.databinding.FragmentGuideLinkBinding
import com.sosnowskydevelop.studyguides.utilities.*
import com.sosnowskydevelop.studyguides.viewmodels.GuideLinkViewModel

class GuideLinkFragment : Fragment() {
    private lateinit var binding: FragmentGuideLinkBinding

    private val viewModel: GuideLinkViewModel by viewModels {
        InjectorUtils.provideGuideLinkViewModelFactory(context = requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuideLinkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        setActionBarTitle(actionBar = actionBar) // Return to the fragment.

        setFragmentResultListener(
            requestKey = REQUEST_KEY_GUIDE_ID_FROM_GUIDES_TO_GUIDE_LINK
        ) { _, bundle ->
            viewModel.initData(
                guideId = bundle.getInt(
                    BUNDLE_KEY_GUIDE_ID_FROM_GUIDES_TO_GUIDE_LINK
                )
            )

            setActionBarTitle(actionBar = actionBar) // First opening of the fragment.

            binding.guideName.text = viewModel.guideName

            binding.guideLink.text = viewModel.guideLink
        }
    }

    private fun setActionBarTitle(actionBar: ActionBar?) {
        actionBar?.title = ""
    }
}