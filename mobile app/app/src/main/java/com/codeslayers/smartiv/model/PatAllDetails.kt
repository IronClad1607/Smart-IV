package com.codeslayers.smartiv.model

import java.io.Serializable

data class PatAllDetails(
    val patID:String,
    val bedNumber: String,
    val consultingDoctor: String,
    val patientAddress: String,
    val patientAge: String,
    val patientBloodGroup: String,
    val patientEmergencyNumber: String,
    val patientGender: String,
    val patientHealthScheme: String,
    val patientInsurance: String,
    val patientMobile: String,
    val patientName: String,
    val patientSymptoms: String,
    val prevMedicalHistory: String,
    val roomNumber: String
) : Serializable