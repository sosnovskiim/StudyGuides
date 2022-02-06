package com.sosnowskydevelop.studyguides.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sosnowskydevelop.studyguides.data.CategoriesRepository

class CategoriesViewModelFactory(
    private val categoriesRepository: CategoriesRepository,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        CategoriesViewModel(
            categoriesRepository = categoriesRepository,
        ) as T
}