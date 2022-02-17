package com.sosnowskydevelop.studyguides.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.studyguides.data.SubcategoriesRepository
import com.sosnowskydevelop.studyguides.data.Subcategory

class SubSubcategoriesViewModel(
    private val subcategoriesRepository: SubcategoriesRepository,
) : ViewModel() {
    private var subcategory: Subcategory? = null
    val subcategoryName: String? get() = subcategory?.name
    var subcategories: Array<Subcategory> = arrayOf()

    fun initData(
        subcategoryId: Int,
    ) {
        subcategory = subcategoriesRepository.getSubcategory(subcategoryId = subcategoryId)
        subcategories = subcategoriesRepository.getSubcategories(parentId = subcategoryId)
    }
}