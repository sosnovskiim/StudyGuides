package com.sosnowskydevelop.studyguides.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sosnowskydevelop.studyguides.data.SubcategoriesRepository

class SubSubcategoriesViewModelFactory(
    private val subcategoriesRepository: SubcategoriesRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        SubSubcategoriesViewModel(
            subcategoriesRepository = subcategoriesRepository,
        ) as T
}