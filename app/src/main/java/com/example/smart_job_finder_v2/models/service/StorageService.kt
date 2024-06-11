package com.example.smart_job_finder_v2.models.service

import com.example.smart_job_finder_v2.models.JobModel
import com.example.smart_job_finder_v2.models.UserData
import kotlinx.coroutines.flow.Flow

interface StorageService {

    val jobs: Flow<List<JobModel>>
    suspend fun createJob(job: JobModel)
    suspend fun deleteJob(jobID: Int)
    suspend fun getUserData(userID: String): UserData

    suspend fun toggleLike(userID: String, jobID: String)
    suspend fun createUserData(userData: UserData)
}