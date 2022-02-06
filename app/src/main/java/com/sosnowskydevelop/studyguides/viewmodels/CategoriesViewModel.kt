package com.sosnowskydevelop.studyguides.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.studyguides.data.Category
import com.sosnowskydevelop.studyguides.data.CategoriesRepository

class CategoriesViewModel(
    categoriesRepository: CategoriesRepository,
) : ViewModel() {
    val categories: Array<Category> = categoriesRepository.getCategories()
}