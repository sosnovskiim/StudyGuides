package com.sosnowskydevelop.studyguides.viewmodels

import androidx.lifecycle.ViewModel
import com.sosnowskydevelop.studyguides.data.Guide
import com.sosnowskydevelop.studyguides.data.GuidesRepository
import com.sosnowskydevelop.studyguides.data.SubcategoriesRepository
import com.sosnowskydevelop.studyguides.data.Subcategory

class GuidesViewModel(
    private val subcategoriesRepository: SubcategoriesRepository,
    private val guidesRepository: GuidesRepository,
) : ViewModel() {
    private var subcategory: Subcategory? = null
    val subcategoryName: String? get() = subcategory?.name
    var guides: Array<Guide> = arrayOf()

    fun initData(
        subcategoryId: Int,
    ) {
        subcategory = subcategoriesRepository.getSubcategory(subcategoryId = subcategoryId)
        guides = guidesRepository.getGuides(subcategoryId = subcategoryId)
    }
}