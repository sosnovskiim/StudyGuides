package com.sosnowskydevelop.studyguides.utilities

import android.content.Context
import com.sosnowskydevelop.studyguides.data.CategoriesRepository
import com.sosnowskydevelop.studyguides.viewmodels.CategoriesViewModelFactory

object InjectorUtils {
    private fun getCategoriesRepository(context: Context) =
        CategoriesRepository.getInstance(context = context)

    fun provideCategoriesViewModelFactory(context: Context) =
        CategoriesViewModelFactory(
            categoriesRepository = getCategoriesRepository(context = context)
        )
}