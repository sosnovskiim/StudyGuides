package com.sosnowskydevelop.studyguides.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sosnowskydevelop.studyguides.R
import com.sosnowskydevelop.studyguides.data.Guide
import com.sosnowskydevelop.studyguides.data.IMAGE
import com.sosnowskydevelop.studyguides.data.LINK
import com.sosnowskydevelop.studyguides.data.PDF
import com.sosnowskydevelop.studyguides.databinding.ListItemGuideBinding
import com.sosnowskydevelop.studyguides.utilities.*
import com.sosnowskydevelop.studyguides.viewmodels.GuideListItemViewModel

class GuidesListAdapter(
    var guides: Array<Guide>,
    private val fragment: Fragment,
) : RecyclerView.Adapter<GuidesListAdapter.ViewHolder>() {
    class ViewHolder(
        val binding: ListItemGuideBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            binding = ListItemGuideBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel =
            GuideListItemViewModel(guide = guides[position])
        holder.binding.guideName.setOnClickListener {
            when (guides[position].type) {
                IMAGE -> {
                    fragment.setFragmentResult(
                        requestKey = REQUEST_KEY_GUIDE_ID_FROM_GUIDES_TO_GUIDE_IMAGE,
                        result = bundleOf(
                            BUNDLE_KEY_GUIDE_ID_FROM_GUIDES_TO_GUIDE_IMAGE
                                    to guides[position].id
                        )
                    )
                    fragment.findNavController()
                        .navigate(R.id.action_guidesFragment_to_guideImageFragment)
                }
                LINK -> {
                    fragment.setFragmentResult(
                        requestKey = REQUEST_KEY_GUIDE_ID_FROM_GUIDES_TO_GUIDE_LINK,
                        result = bundleOf(
                            BUNDLE_KEY_GUIDE_ID_FROM_GUIDES_TO_GUIDE_LINK
                                    to guides[position].id
                        )
                    )
                    fragment.findNavController()
                        .navigate(R.id.action_guidesFragment_to_guideLinkFragment)
                }
                PDF -> {
                    fragment.setFragmentResult(
                        requestKey = REQUEST_KEY_GUIDE_ID_FROM_GUIDES_TO_GUIDE_PDF,
                        result = bundleOf(
                            BUNDLE_KEY_GUIDE_ID_FROM_GUIDES_TO_GUIDE_PDF
                                    to guides[position].id
                        )
                    )
                    fragment.findNavController()
                        .navigate(R.id.action_guidesFragment_to_guidePdfFragment)
                }
            }
        }
    }

    override fun getItemCount(): Int = guides.size
}