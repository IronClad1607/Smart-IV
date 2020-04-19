package com.codeslayers.smartiv.searchpatient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.codeslayers.smartiv.R
import com.codeslayers.smartiv.model.PatAllDetails
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_create_entry.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.etPatientID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchActivity : AppCompatActivity() {

    private val db by lazy {
        FirebaseDatabase.getInstance("https://smart-iv-hackon-patientid.firebaseio.com/")
    }

    private lateinit var details: PatAllDetails
    private var mDelayHandler: Handler? = null
    private var DELAY: Long = 1000

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val comIntent = Intent(this, SearchAllPatientActivity::class.java)
            comIntent.putExtra("searchAllDetails", details)
            startActivity(comIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        btnPatSubmit.setOnClickListener {
            val patID = etPatientID.editText?.text.toString()

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
                details = PatAllDetails(
                    ds.key.toString(),
                    ds.child("bedNumber").value.toString(),
                    ds.child("consultingDoctor").value.toString(),
                    ds.child("patientAddress").value.toString(),
                    ds.child("patientAge").value.toString(),
                    ds.child("patientBloodGroup").value.toString(),
                    ds.child("patientEmergencyNumber").value.toString(),
                    ds.child("patientGender").value.toString(),
                    ds.child("patientHealthScheme").value.toString(),
                    ds.child("patientInsurance").value.toString(),
                    ds.child("patientMobile").value.toString(),
                    ds.child("patientName").value.toString(),
                    ds.child("patientSymptoms").value.toString(),
                    ds.child("prevMedicalHistory").value.toString(),
                    ds.child("roomNumber").value.toString()
                )

                runOnUiThread {
                    btnPatSubmit.doResult(true)
                }
            }
        }

        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, DELAY)

    }
}
