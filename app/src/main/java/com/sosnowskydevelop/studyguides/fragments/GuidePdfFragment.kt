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
import com.sosnowskydevelop.studyguides.databinding.FragmentGuidePdfBinding
import com.sosnowskydevelop.studyguides.utilities.BUNDLE_KEY_GUIDE_ID_FROM_GUIDES_TO_GUIDE_PDF
import com.sosnowskydevelop.studyguides.utilities.InjectorUtils
import com.sosnowskydevelop.studyguides.utilities.REQUEST_KEY_GUIDE_ID_FROM_GUIDES_TO_GUIDE_PDF
import com.sosnowskydevelop.studyguides.viewmodels.GuidePdfViewModel

class GuidePdfFragment : Fragment() {
    private val viewModel: GuidePdfViewModel by viewModels {
        InjectorUtils.provideGuidePdfViewModelFactory(context = requireContext())
    }

    private lateinit var binding: FragmentGuidePdfBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuidePdfBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        // Return to the fragment.
        setActionBarTitle(actionBar = actionBar)
        setGuideImage()

        setFragmentResultListener(
            requestKey = REQUEST_KEY_GUIDE_ID_FROM_GUIDES_TO_GUIDE_PDF
        ) { _, bundle ->
            viewModel.initData(
                guideId = bundle.getInt(
                    BUNDLE_KEY_GUIDE_ID_FROM_GUIDES_TO_GUIDE_PDF
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
            val guidePdfResId: Int = resources.getIdentifier(
                viewModel.guideFileName,
                "raw",
                "com.sosnowskydevelop.studyguides"
            )
            binding.guidePdf.fromStream(
                resources.openRawResource(guidePdfResId)
            ).load()
        }
    }
}