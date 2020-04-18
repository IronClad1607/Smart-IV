package com.codeslayers.smartiv.createentry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.codeslayers.smartiv.R
import com.codeslayers.smartiv.ui.HomeActivity
import com.codeslayers.smartiv.ui.LoginActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_create_entry.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateEntryActivity : AppCompatActivity() {

    var patID: String = ""
    var patName: String = ""
    var patGen: String = ""
    var patPN: String = ""
    var patAge: String = ""
    var patBG: String = ""
    var patEN: String = ""
    var patDoc: String = ""
    var patSym: String = ""
    var roomNumber: String = ""
    var bedNumber: String = ""
    var isExist: Boolean = false


    private val dbPatActive by lazy {
        FirebaseDatabase.getInstance("https://smart-iv-hackon-patientid.firebaseio.com/")
    }

    private val dbPatDrip by lazy {
        FirebaseDatabase.getInstance("https://smart-iv-hackon.firebaseio.com/")
    }

//    private var mDelayHandler: Handler? = null
//    private var DELAY: Long = 2000
//
//    private val mRunnable: Runnable = Runnable {
//        if (!isFinishing) {
//            val detailIntent = Intent(this, PatientDetailActivity::class.java)
//            detailIntent.putExtra("patID", patID)
//            detailIntent.putExtra("patName", patName)
//            detailIntent.putExtra("patGen", patGen)
//            detailIntent.putExtra("patPN", patPN)
//            detailIntent.putExtra("patAge", patAge)
//            detailIntent.putExtra("patBG", patBG)
//            detailIntent.putExtra("patEN", patEN)
//            detailIntent.putExtra("patDoc", patDoc)
//            detailIntent.putExtra("patSym", patSym)
//            detailIntent.putExtra("roomNum", roomNumber)
//            detailIntent.putExtra("bedNum", bedNumber)
//
//            startActivity(detailIntent)
//        }
//    }

    override fun onStart() {
        super.onStart()
        btnPatID.reset()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_entry)

        btnPatID.setOnClickListener {
            patID = etPatientID.editText?.text.toString()

            GlobalScope.launch(Dispatchers.Main) {


                val myDripRef = dbPatDrip.reference
                myDripRef.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        val roomNumbers = p0.children.iterator()
                        while (roomNumbers.hasNext()) {
                            val roomNumber = roomNumbers.next()
                            val bedNumbers = roomNumber.children.iterator()
                            while (bedNumbers.hasNext()) {
                                val bedNumber = bedNumbers.next()
                                if (bedNumber.child("patientID").value == patID) {
                                    isExist = true
                                    break
                                } else {
                                    val myRef = dbPatActive.reference
                                    myRef.addValueEventListener(object : ValueEventListener {
                                        override fun onCancelled(p0: DatabaseError) {
                                            btnPatID.doResult(false)
                                        }

                                        override fun onDataChange(p0: DataSnapshot) {
                                            showData(p0, patID)
                                        }

                                    })
                                }
                            }
                        }
                    }
                })


            }


            Log.d("PUI", "$isExist")

            if (isExist) {
                Toast.makeText(this, "IV is already attached to the patient", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val detailIntent = Intent(this, PatientDetailActivity::class.java)
                detailIntent.putExtra("patID", patID)
                detailIntent.putExtra("patName", patName)
                detailIntent.putExtra("patGen", patGen)
                detailIntent.putExtra("patPN", patPN)
                detailIntent.putExtra("patAge", patAge)
                detailIntent.putExtra("patBG", patBG)
                detailIntent.putExtra("patEN", patEN)
                detailIntent.putExtra("patDoc", patDoc)
                detailIntent.putExtra("patSym", patSym)
                detailIntent.putExtra("roomNum", roomNumber)
                detailIntent.putExtra("bedNum", bedNumber)

                startActivity(detailIntent)
            }

        }
    }

    private fun showData(snapshot: DataSnapshot, patID: String) {
        for (ds in snapshot.children) {
            if (ds.key == patID) {
                patName = ds.child("patientName").value.toString()
                patGen = ds.child("patientGender").value.toString()
                patPN = ds.child("patientMobile").value.toString()
                patAge = ds.child("patientAge").value.toString()
                patEN = ds.child("patientEmergencyNumber").value.toString()
                patDoc = ds.child("consultingDoctor").value.toString()
                patBG = ds.child("patientBloodGroup").value.toString()
                patSym = ds.child("patientSymptoms").value.toString()
                roomNumber = ds.child("roomNumber").value.toString()
                bedNumber = ds.child("bedNumber").value.toString()


                btnPatID.doResult(true)
            }

        }
    }


    override fun onBackPressed() {
        val backIntent = Intent(this, HomeActivity::class.java)
        startActivity(backIntent)
    }
}
