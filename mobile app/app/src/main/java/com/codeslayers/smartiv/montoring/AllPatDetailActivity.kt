package com.codeslayers.smartiv.montoring

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.codeslayers.smartiv.R
import com.codeslayers.smartiv.model.DripDetails
import kotlinx.android.synthetic.main.activity_all_pat_detail.*
import kotlinx.android.synthetic.main.cv_all_details_personal.*
import kotlinx.android.synthetic.main.cv_alldetials_med.*

class AllPatDetailActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_pat_detail)

        btnDripDelete.isEnabled = false

        val patDetails = intent.getSerializableExtra("allDetails") as DripDetails
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
    }
}
