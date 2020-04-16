package com.codeslayers.smartiv.createentry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.codeslayers.smartiv.R
import com.codeslayers.smartiv.model.PatDetails
import com.codeslayers.smartiv.ui.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_patient_detail.*
import kotlinx.android.synthetic.main.cv_drip_spinner.*
import kotlinx.android.synthetic.main.cv_patient_details.*
import kotlinx.android.synthetic.main.cv_patient_medical.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PatientDetailActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private var DELAY: Long = 1000

    private val dbDrip by lazy {
        FirebaseDatabase.getInstance("https://smart-iv-hackon.firebaseio.com/")
    }


    private val nurseUser by lazy {
        FirebaseAuth.getInstance().currentUser
    }

    var patDripName: String = ""

    val dripName = arrayOf(
        "0.9% Normal Saline",
        "Lactated Ringers(RL)",
        "Dextrose in Water",
        "Dextrose in Saline",
        "Dextrose in LP",
        "0.45% Normal Saline",
        "Glucose",
        "None"
    )

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val backIntent =
                Intent(this@PatientDetailActivity, HomeActivity::class.java)
            startActivity(backIntent)
        }
    }

    override fun onStart() {
        super.onStart()
        btnDripSubmit.reset()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_detail)

        val patID = intent.getStringExtra("patID")!!
        val patName = intent.getStringExtra("patName")!!
        val patGen = intent.getStringExtra("patGen")!!
        val patPN = intent.getStringExtra("patPN")!!
        val patAge = intent.getStringExtra("patAge")!!
        val patBG = intent.getStringExtra("patBG")!!
        val patEN = intent.getStringExtra("patEN")!!
        val patDoc = intent.getStringExtra("patDoc")!!
        val patSym = intent.getStringExtra("patSym")!!
        val roomNumber = intent.getStringExtra("roomNum")!!
        val bedNumber = intent.getStringExtra("bedNum")!!

        runOnUiThread {
            tvPatID.text = patID
            tvPatName.text = patName
            tvPatGen.text = patGen
            tvPhoneNumber.text = patPN
            tvPatAge.text = patAge
            tvPatEN.text = patEN
            tvPatDoc.text = patDoc
            tvPatBG.text = patBG
            tvPatSym.text = patSym
            tvRN.text = roomNumber
            tvBN.text = bedNumber
        }

        dripSpinner.adapter =
            ArrayAdapter<String>(this, R.layout.item_spinner, dripName)

        dripSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(
                    this@PatientDetailActivity,
                    "Please Select A IV Fluid!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                patDripName = dripName[position]
                if (patDripName == "None") {
                    etDripSpec.visibility = View.VISIBLE
                } else {
                    etDripSpec.visibility = View.GONE
                }
            }
        }

        btnDripSubmit.setOnClickListener {
            if (etDripSpec.visibility == View.VISIBLE) {
                patDripName = etDripSpec.editText?.text.toString()
            }
            val dripStatus: Boolean = false

            val child = PatDetails(
                patName,
                patDoc,
                patID,
                nurseUser?.email.toString(),
                patBG,
                patSym,
                patDripName,
                dripStatus
            )

            GlobalScope.launch(Dispatchers.Main) {
                val myRef = dbDrip.reference

                val dripAdd = myRef.child("Room Number $roomNumber").child("Bed Number $bedNumber")
                dripAdd.setValue(child)
                    .addOnSuccessListener {
                        runOnUiThread {
                            btnDripSubmit.doResult(true)
                        }

                        mDelayHandler = Handler()
                        mDelayHandler!!.postDelayed(mRunnable, DELAY)

                    }
            }
        }

    }


    override fun onBackPressed() {
        val backIntent = Intent(this, HomeActivity::class.java)
        startActivity(backIntent)
    }
}
