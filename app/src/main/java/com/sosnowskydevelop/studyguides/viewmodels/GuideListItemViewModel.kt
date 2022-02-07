package com.sosnowskydevelop.studyguides.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.studyguides.data.Guide

class GuideListItemViewModel(
    private val guide: Guide,
) : ViewModel() {
    val guideName: String get() = guide.name
}