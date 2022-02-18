package com.sosnowskydevelop.studyguides.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.sosnowskydevelop.studyguides.databinding.FragmentGuideImageBinding
import com.sosnowskydevelop.studyguides.utilities.BUNDLE_KEY_GUIDE_ID_FROM_GUIDES_TO_GUIDE_IMAGE
import com.sosnowskydevelop.studyguides.utilities.InjectorUtils
import com.sosnowskydevelop.studyguides.utilities.REQUEST_KEY_GUIDE_ID_FROM_GUIDES_TO_GUIDE_IMAGE
import com.sosnowskydevelop.studyguides.viewmodels.GuideImageViewModel

class GuideImageFragment : Fragment() {
    private val viewModel: GuideImageViewModel by viewModels {
        InjectorUtils.provideGuideImageViewModelFactory(context = requireContext())
    }

    private lateinit var binding: FragmentGuideImageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuideImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        // Return to the fragment.
        setActionBarTitle(actionBar = actionBar)
        setGuideImage()

        setFragmentResultListener(
            requestKey = REQUEST_KEY_GUIDE_ID_FROM_GUIDES_TO_GUIDE_IMAGE
        ) { _, bundle ->
            viewModel.initData(
                guideId = bundle.getInt(
                    BUNDLE_KEY_GUIDE_ID_FROM_GUIDES_TO_GUIDE_IMAGE
                )
            )

            // First opening of the fragment.
            setActionBarTitle(actionBar = actionBar)
            setGuideImage()

            binding.guideName.text = viewModel.guideName
        }
    }

    private fun setActionBarTitle(actionBar: ActionBar?) {
        actionBar?.title = ""
    }

    private fun setGuideImage() {
        if (viewModel.guideFileName != null) {
            val guideImageResId: Int = resources.getIdentifier(
                viewModel.guideFileName,
                "drawable",
                "com.sosnowskydevelop.studyguides"
            )
            binding.guideImage.setImageResource(guideImageResId)
        }
    }
}