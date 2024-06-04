package com.example.smart_job_finder_v2.models

data class UserData(

    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val profileImageURL: String = "",
    val likedJobID: List<String> = emptyList()
)
