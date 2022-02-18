package com.sosnowskydevelop.studyguides.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.studyguides.data.Guide
import com.sosnowskydevelop.studyguides.data.GuidesRepository

class GuidePdfViewModel(
    private val guidesRepository: GuidesRepository,
) : ViewModel() {
    private var guide: Guide? = null
    val guideName: String? get() = guide?.name
    val guideFileName: String? get() = guide?.value

    fun initData(
        guideId: Int,
    ) {
        guide = guidesRepository.getGuide(guideId = guideId)
    }
}