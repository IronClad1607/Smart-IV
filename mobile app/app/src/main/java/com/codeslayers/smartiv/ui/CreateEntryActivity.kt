package com.codeslayers.smartiv.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codeslayers.smartiv.R
import kotlinx.android.synthetic.main.activity_create_entry.*

class CreateEntryActivity : AppCompatActivity() {

    val dripName = arrayOf(
        "0.9% Normal Saline",
        "Lactated Ringers(RL)",
        "Dextrose in Water",
        "Dextrose in Saline",
        "Dextrose in LP",
        "0.45% Normal Saline",
        "Glucose"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_entry)


        btnSubmit.setOnClickListener {
            val patName = etPatName.editText?.text.toString()
            val patGen = etPatGen.editText?.text.toString()
            val roomNumber = etRoomNum.editText?.text.toString()
            val bedNumber = etBedNum.editText?.text.toString()
            val consultingDoctor = etDocNam.editText?.text.toString()
            val patAge = etPatAge.editText?.text.toString()
            val patBG = etPatBG.editText?.text.toString()
            val patDis = etPatDis.editText?.text.toString()
            val patSpecialDrip = "None"
            val emergencyNumber = etPatEN.editText?.text.toString()
        }
    }
}
