package com.codeslayers.smartiv.model

import java.io.Serializable

data class DripDetails(
    val roomNumber: String,
    val bedNumber: String,
    val consultingDoctor: String,
    val dripStatus: Boolean,
    val nurseID: String,
    val patBG: String,
    val patientID: String,
    val patientIVFluid: String,
    val patientName: String,
    val patientSymptoms: String
) : Serializable