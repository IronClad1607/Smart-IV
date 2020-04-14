package com.codeslayers.smartiv.model

data class PatDetails(
    val PatientName: String,
    val PatientGender: String,
    val ConsultingDoctor: String,
    val PatientAge: String,
    val PatientBloodGroup: String,
    val PatientDisease: String,
    val PatientEN: String,
    val PatientIVFluid: String,
    val DripStatus: Boolean
)