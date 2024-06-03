package com.example.smart_job_finder_v2.models.service

import com.example.smart_job_finder_v2.models.JobModel
import kotlinx.coroutines.flow.Flow

interface StorageService {

    val jobs: Flow<List<JobModel>>
    suspend fun createJob(job: JobModel)
    suspend fun readJob(jobID: Int)
    suspend fun updateJob(job: JobModel)
    suspend fun deleteJob(jobID: Int)


}