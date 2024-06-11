package com.example.smart_job_finder_v2.models.service.impl

import com.example.smart_job_finder_v2.models.JobModel
import com.example.smart_job_finder_v2.models.UserData
import com.example.smart_job_finder_v2.models.service.AccountService
import com.example.smart_job_finder_v2.models.service.StorageService
import com.google.firebase.Firebase
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(private val auth: AccountService) : StorageService {
    override val jobs: Flow<List<JobModel>>
        get() = Firebase.firestore.collection(JOB_COLLECTION).dataObjects()

    override suspend fun createJob(job: JobModel) {
        Firebase.firestore.collection(JOB_COLLECTION).add(job)
    }


    override suspend fun deleteJob(jobID: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserData(userID: String): UserData {
        val userRef = Firebase.firestore.collection(USERDATA_COLLECTION).document(userID)
        return userRef.get().await().toObject(UserData::class.java)!!
    }

    override suspend fun toggleLike(userID: String, jobID: String) {
        val userRef = Firebase.firestore.collection(USERDATA_COLLECTION).document(userID)
        val userData = userRef.get().await().toObject(UserData::class.java)!!

        val newLikedJobID = userData.likedJobID.toMutableList()

        if (!newLikedJobID.contains(jobID)) {
            newLikedJobID.add(jobID)
        } else {
            newLikedJobID.remove(jobID)
        }

        userRef.update("likedJobID", newLikedJobID)
    }

    override suspend fun createUserData(userData: UserData) {
        val userID = auth.currentUserId
        Firebase.firestore.collection(USERDATA_COLLECTION).document(userID).set(userData)
    }

    companion object {
        private const val JOB_COLLECTION = "jobs"
        private const val USERDATA_COLLECTION = "users"
    }

}