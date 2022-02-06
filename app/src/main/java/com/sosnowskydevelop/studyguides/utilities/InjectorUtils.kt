package com.sosnowskydevelop.studyguides.utilities

import android.content.Context
import com.sosnowskydevelop.studyguides.data.CategoriesRepository
import com.sosnowskydevelop.studyguides.data.SubcategoriesRepository
import com.sosnowskydevelop.studyguides.viewmodels.CategoriesViewModelFactory
import com.sosnowskydevelop.studyguides.viewmodels.SubcategoriesViewModelFactory

object InjectorUtils {
    private fun getCategoriesRepository(context: Context) =
        CategoriesRepository.getInstance(context = context)

    private fun getSubcategoriesRepository(context: Context) =
        SubcategoriesRepository.getInstance(context = context)

    fun provideCategoriesViewModelFactory(context: Context) =
        CategoriesViewModelFactory(
            categoriesRepository = getCategoriesRepository(context = context),
        )

    fun provideSubcategoriesViewModelFactory(context: Context) =
        SubcategoriesViewModelFactory(
            categoriesRepository = getCategoriesRepository(context = context),
            subcategoriesRepository = getSubcategoriesRepository(context = context),
        )
}