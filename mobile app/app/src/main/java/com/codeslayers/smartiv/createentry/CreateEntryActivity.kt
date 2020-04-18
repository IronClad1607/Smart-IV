package com.codeslayers.smartiv.createentry

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.codeslayers.smartiv.R
import com.codeslayers.smartiv.ui.HomeActivity
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


    private val db by lazy {
        FirebaseDatabase.getInstance("https://smart-iv-hackon-patientid.firebaseio.com/")
    }

    private var mDelayHandler: Handler? = null
    private var DELAY: Long = 2000

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
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
                val myRef = db.reference
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


                runOnUiThread {
                    btnPatID.doResult(true)
                }
            }

            mDelayHandler = Handler()
            mDelayHandler!!.postDelayed(mRunnable, DELAY)

        }
    }

    override fun onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }

    override fun onBackPressed() {
        val backIntent = Intent(this, HomeActivity::class.java)
        startActivity(backIntent)
    }
}