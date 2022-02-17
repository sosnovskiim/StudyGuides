package com.sosnowskydevelop.studyguides.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sosnowskydevelop.studyguides.data.GuidesRepository

class GuideLinkViewModelFactory(
    private val guidesRepository: GuidesRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        GuideLinkViewModel(
            guidesRepository = guidesRepository,
        ) as T
}