package com.codeslayers.smartiv.searchpatient

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.codeslayers.smartiv.HomeActivity
import com.codeslayers.smartiv.R
import com.codeslayers.smartiv.model.PatAllDetails
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_search_all_patient.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchAllPatientActivity : AppCompatActivity() {

    val dbDrip by lazy {
        FirebaseDatabase.getInstance("https://smart-iv-hackon.firebaseio.com/")
    }


    override fun onBackPressed() {
        val backIntent = Intent(this, HomeActivity::class.java)
        startActivity(backIntent)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_all_patient)
        val allDetails = intent.getSerializableExtra("searchAllDetails") as PatAllDetails


        GlobalScope.launch(Dispatchers.Main) {
            val mRef = dbDrip.reference
            mRef.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(p0: DataSnapshot) {
                    val roomNumbers = p0.children.iterator()
                    while (roomNumbers.hasNext()) {
                        val roomNumber = roomNumbers.next()
                        val bedNumbers = roomNumber.children.iterator()
                        while (bedNumbers.hasNext()) {
                            val bedNumber = bedNumbers.next()
                            if (bedNumber.child("patientID").value.toString() == allDetails.patID) {
                                tvPatNurse.text = bedNumber.child("nurseID").value.toString()
                                tvDripName.text = bedNumber.child("patientIVFluid").value.toString()

                                if (bedNumber.child("dripStatus").value.toString().toBoolean()) {
                                    tvDripStatus.text = "At Critical Level"
                                } else {
                                    tvDripStatus.text = "At Safe Level"
                                }
                            }
                        }
                    }
                }
            })
        }



        tvPatID.text = allDetails.patID
        tvPatName.text = allDetails.patientName
        tvPatGen.text = allDetails.patientGender
        tvPhoneNumber.text = allDetails.patientMobile
        tvPatEN.text = allDetails.patientEmergencyNumber
        tvPatHS.text = allDetails.patientHealthScheme
        tvInsurance.text = allDetails.patientInsurance
        tvPatDoc.text = allDetails.consultingDoctor
        tvPatBG.text = allDetails.patientBloodGroup
        tvPatSym.text = allDetails.patientSymptoms
        tvPatPrev.text = allDetails.prevMedicalHistory
        tvRN.text = allDetails.roomNumber
        tvBN.text = allDetails.bedNumber
    }
}
