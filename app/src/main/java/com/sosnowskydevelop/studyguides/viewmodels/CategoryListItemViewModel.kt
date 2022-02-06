package com.sosnowskydevelop.studyguides.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.studyguides.data.Category

class CategoryListItemViewModel(
    private val category: Category
) : ViewModel() {
    val categoryName: String get() = category.name
}