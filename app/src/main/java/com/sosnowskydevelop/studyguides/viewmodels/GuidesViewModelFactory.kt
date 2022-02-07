package com.sosnowskydevelop.studyguides.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sosnowskydevelop.studyguides.data.GuidesRepository
import com.sosnowskydevelop.studyguides.data.SubcategoriesRepository

class GuidesViewModelFactory(
    private val subcategoriesRepository: SubcategoriesRepository,
    private val guidesRepository: GuidesRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        GuidesViewModel(
            subcategoriesRepository = subcategoriesRepository,
            guidesRepository = guidesRepository,
        ) as T
}