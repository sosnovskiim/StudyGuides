package com.sosnowskydevelop.studyguides.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.studyguides.data.CategoriesRepository
import com.sosnowskydevelop.studyguides.data.Category
import com.sosnowskydevelop.studyguides.data.SubcategoriesRepository
import com.sosnowskydevelop.studyguides.data.Subcategory

class SubcategoriesViewModel(
    private val categoriesRepository: CategoriesRepository,
    private val subcategoriesRepository: SubcategoriesRepository,
) : ViewModel() {
    private var category: Category? = null
    val categoryName: String? get() = category?.name
    var subcategories: Array<Subcategory> = arrayOf()

    fun initData(
        categoryId: Int,
    ) {
        category = categoriesRepository.getCategory(categoryId = categoryId)
        subcategories = subcategoriesRepository.getSubcategories(categoryId = categoryId)
    }
}