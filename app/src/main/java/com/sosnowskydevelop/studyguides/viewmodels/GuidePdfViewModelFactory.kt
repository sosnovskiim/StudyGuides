package com.sosnowskydevelop.studyguides.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sosnowskydevelop.studyguides.data.GuidesRepository

class GuidePdfViewModelFactory(
    private val guidesRepository: GuidesRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        GuidePdfViewModel(
            guidesRepository = guidesRepository,
        ) as T
}