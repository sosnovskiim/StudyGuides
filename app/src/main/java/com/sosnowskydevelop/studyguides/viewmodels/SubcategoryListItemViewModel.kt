package com.sosnowskydevelop.studyguides.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.studyguides.data.Subcategory

class SubcategoryListItemViewModel(
    private val subcategory: Subcategory,
) : ViewModel() {
    val subcategoryName: String get() = subcategory.name
}