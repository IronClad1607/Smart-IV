package com.codeslayers.smartiv.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.codeslayers.smartiv.R
import com.codeslayers.smartiv.model.PatDetails
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_entry.*
import kotlinx.android.synthetic.main.activity_create_entry.view.*

class CreateEntryActivity : AppCompatActivity() {


    private val db by lazy {
        FirebaseDatabase.getInstance()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_entry)

        etDripSpec.visibility = View.GONE

        spinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dripName)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(
                    this@CreateEntryActivity,
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


        btnSubmit.setOnClickListener {
            val patName = etPatName.editText?.text.toString()
            val patGen = etPatGen.editText?.text.toString()
            val roomNumber = etRoomNum.editText?.text.toString()
            val bedNumber = etBedNum.editText?.text.toString()
            val consultingDoctor = etDocNam.editText?.text.toString()
            val patAge = etPatAge.editText?.text.toString()
            val patBG = etPatBG.editText?.text.toString()
            val patDis = etPatDis.editText?.text.toString()
            val emergencyNumber = etPatEN.editText?.text.toString()
            if (etDripSpec.visibility == View.VISIBLE) {
                patDripName = etDripSpec.editText?.text.toString()
            }
            val dripStatus: Boolean = false

            val child = PatDetails(
                patName,
                patGen,
                consultingDoctor,
                patAge,
                patBG,
                patDis,
                emergencyNumber,
                patDripName,
                dripStatus
            )

            Log.d(
                "PUI", """
                $patName
                $patGen
                $roomNumber
                $bedNumber
                $consultingDoctor
                $patAge
                $patBG
                $patDis
                $emergencyNumber
                $patDripName
            """.trimIndent()
            )


            val myRef = db.reference

            val patValue = myRef.child("Room Number $roomNumber").child("Bed Number $bedNumber")
            patValue.setValue(child)
                .addOnCompleteListener {

                }
                .addOnSuccessListener {
                    Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
