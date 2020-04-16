package com.codeslayers.smartiv.createentry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codeslayers.smartiv.R
import com.codeslayers.smartiv.ui.HomeActivity
import kotlinx.android.synthetic.main.activity_create_entry.*

class CreateEntryActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_entry)

        btnSubPatID.setOnClickListener {
            val patId = etPatient.editText?.text.toString()
            val patIdIntent = Intent(this, PatientDetailActivity::class.java)
            patIdIntent.putExtra("patId", patId)
            startActivity(patIdIntent)
        }
    }


    override fun onBackPressed() {
        val backIntent = Intent(this, HomeActivity::class.java)
        startActivity(backIntent)
    }
}
