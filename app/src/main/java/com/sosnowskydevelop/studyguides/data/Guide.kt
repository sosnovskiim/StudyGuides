package com.sosnowskydevelop.studyguides.data

data class Guide(
    val id: Int,
    val parentId: Int,
    val name: String,
    val value: String,
    val type: String,
)