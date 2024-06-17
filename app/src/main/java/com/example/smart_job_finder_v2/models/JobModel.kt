package com.example.smart_job_finder_v2.models

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

data class JobModel(
    @DocumentId val id: String = "",
    val title: String = "",
    val company: String = "",
    val location: String = "",
    val type: String = "",
    val description: String = "",
    val imageURL: String = "",
    val email: String = "",
    val postedDate: Timestamp = Timestamp.now(),
)