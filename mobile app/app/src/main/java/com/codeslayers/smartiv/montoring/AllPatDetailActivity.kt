package com.codeslayers.smartiv.montoring

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.codeslayers.smartiv.HomeActivity
import com.codeslayers.smartiv.R
import com.codeslayers.smartiv.model.DripDetails
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_all_pat_detail.*
import kotlinx.android.synthetic.main.cv_all_details_personal.*
import kotlinx.android.synthetic.main.cv_alldetials_med.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AllPatDetailActivity : AppCompatActivity() {

    val dbDrip by lazy {
        FirebaseDatabase.getInstance("https://smart-iv-hackon.firebaseio.com/")
    }

    private var mDelayHandler: Handler? = null
    private var DELAY: Long = 1000

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val comIntent = Intent(this, HomeActivity::class.java)
            startActivity(comIntent)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_pat_detail)

        btnDripDelete.isEnabled = false

        val patDetails = intent.getSerializableExtra("allDetails") as DripDetails

        val patID = patDetails.patientID
        tvPatID.text = patDetails.patientID
        tvPatName.text = patDetails.patientName
        tvPatBG.text = patDetails.patBG
        tvRN.text = patDetails.roomNumber
        tvBN.text = patDetails.bedNumber
        tvPatDoc.text = patDetails.consultingDoctor
        tvPatSym.text = patDetails.patientSymptoms
        tvDripName.text = patDetails.patientIVFluid

        if (patDetails.dripStatus) {
            btnDripDelete.visibility = View.VISIBLE
            btnDripDelete.isEnabled = true
            tvDripStatus.text = "At Critical Level"
        } else {
            btnDripDelete.visibility = View.GONE
            btnDripDelete.isEnabled = false
            tvDripStatus.text = "At Safe Level"
        }


        if (btnDripDelete.isEnabled) {
            btnDripDelete.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    val mRef = dbDrip.reference

                    mRef.addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(p0: DatabaseError) {

                        }

                        override fun onDataChange(p0: DataSnapshot) {
                            val roomNumbers = p0.children.iterator()
                            test@ while (roomNumbers.hasNext()) {
                                val roomNumber = roomNumbers.next()
                                val bedNumbers = roomNumber.children.iterator()
                                while (bedNumbers.hasNext()) {
                                    val bedNumber = bedNumbers.next()
                                    if (bedNumber.child("patientID").value.toString() == patID) {
                                        mRef.child(roomNumber.key!!).removeValue()
                                            .addOnSuccessListener {
                                                btnDripDelete.doResult(true)

                                                mDelayHandler = Handler()
                                                mDelayHandler!!.postDelayed(mRunnable, DELAY)
                                            }
                                        break@test
                                    }
                                }
                            }
                        }
                    })
                }
            }
        }
    }

    override fun onBackPressed() {
        val backIntent = Intent(this, HomeActivity::class.java)
        startActivity(backIntent)
    }
}
