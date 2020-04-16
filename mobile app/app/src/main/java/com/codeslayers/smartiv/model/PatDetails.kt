package com.codeslayers.smartiv.model

data class PatDetails(
    val PatientName: String,
    val ConsultingDoctor: String,
    val PatientID: String,
    val NurseID: String,
    val PatientBloodGroup: String,
    val PatientSymptoms: String,
    val PatientIVFluid: String,
    val DripStatus: Boolean
)