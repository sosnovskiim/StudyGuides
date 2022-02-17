package com.sosnowskydevelop.studyguides.utilities

import android.content.Context
import com.sosnowskydevelop.studyguides.data.CategoriesRepository
import com.sosnowskydevelop.studyguides.data.GuidesRepository
import com.sosnowskydevelop.studyguides.data.SubcategoriesRepository
import com.sosnowskydevelop.studyguides.viewmodels.*

object InjectorUtils {
    private fun getCategoriesRepository(context: Context) =
        CategoriesRepository.getInstance(context = context)

    private fun getSubcategoriesRepository(context: Context) =
        SubcategoriesRepository.getInstance(context = context)

    private fun getGuidesRepository(context: Context) =
        GuidesRepository.getInstance(context = context)

    fun provideCategoriesViewModelFactory(context: Context) =
        CategoriesViewModelFactory(
            categoriesRepository = getCategoriesRepository(context = context),
        )

    fun provideSubcategoriesViewModelFactory(context: Context) =
        SubcategoriesViewModelFactory(
            categoriesRepository = getCategoriesRepository(context = context),
            subcategoriesRepository = getSubcategoriesRepository(context = context),
        )

    fun provideSubSubcategoriesViewModelFactory(context: Context) =
        SubSubcategoriesViewModelFactory(
            subcategoriesRepository = getSubcategoriesRepository(context = context),
        )

    fun provideGuidesViewModelFactory(context: Context) =
        GuidesViewModelFactory(
            subcategoriesRepository = getSubcategoriesRepository(context = context),
            guidesRepository = getGuidesRepository(context = context),
        )

    fun provideGuideLinkViewModelFactory(context: Context) =
        GuideLinkViewModelFactory(
            guidesRepository = getGuidesRepository(context = context),
        )
}