package com.sosnowskydevelop.studyguides.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sosnowskydevelop.studyguides.data.CategoriesRepository
import com.sosnowskydevelop.studyguides.data.SubcategoriesRepository

class SubcategoriesViewModelFactory(
    private val categoriesRepository: CategoriesRepository,
    private val subcategoriesRepository: SubcategoriesRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        SubcategoriesViewModel(
            categoriesRepository = categoriesRepository,
            subcategoriesRepository = subcategoriesRepository,
        ) as T
}