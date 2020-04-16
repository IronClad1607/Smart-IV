package com.codeslayers.smartiv.createentry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.codeslayers.smartiv.R
import kotlinx.android.synthetic.main.cv_drip_spinner.*
import kotlinx.android.synthetic.main.cv_patient_details.*
import kotlinx.android.synthetic.main.cv_patient_medical.*

class PatientDetailActivity : AppCompatActivity() {

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_detail)

        var patID = intent.getStringExtra("patID")!!
        var patName = intent.getStringExtra("patName")!!
        var patGen = intent.getStringExtra("patGen")!!
        var patPN = intent.getStringExtra("patPN")!!
        var patAge = intent.getStringExtra("patAge")!!
        var patBG = intent.getStringExtra("patBG")!!
        var patEN = intent.getStringExtra("patEN")!!
        var patDoc = intent.getStringExtra("patDoc")!!
        var patSym = intent.getStringExtra("patSym")!!
        var roomNumber = intent.getStringExtra("roomNum")!!
        var bedNumber = intent.getStringExtra("bedNum")!!

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
    }
}
