package com.example.smart_job_finder_v2.models.service.impl

import com.example.smart_job_finder_v2.models.JobModel
import com.example.smart_job_finder_v2.models.service.AccountService
import com.example.smart_job_finder_v2.models.service.StorageService
import com.google.firebase.Firebase
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(private val auth: AccountService) : StorageService {
    override val jobs: Flow<List<JobModel>>
        get() = Firebase.firestore.collection(JOB_COLLECTION).dataObjects()

    override suspend fun createJob(job: JobModel) {
        TODO("Not yet implemented")
    }

    override suspend fun readJob(jobID: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateJob(job: JobModel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteJob(jobID: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val JOB_COLLECTION = "jobs"
    }

}