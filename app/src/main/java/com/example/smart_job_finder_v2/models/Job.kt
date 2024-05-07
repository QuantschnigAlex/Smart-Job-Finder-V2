package com.example.smart_job_finder_v2.models

data class Job (
    val id: Int,
    val title: String,
    val company: String,
    val location: String,
    val type: String,
    val description: String,
    val postedDate: String,
    )