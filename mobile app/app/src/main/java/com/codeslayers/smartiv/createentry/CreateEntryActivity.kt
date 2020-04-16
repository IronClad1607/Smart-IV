package com.codeslayers.smartiv.createentry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codeslayers.smartiv.R
import com.codeslayers.smartiv.ui.HomeActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_create_entry.*

class CreateEntryActivity : AppCompatActivity() {
    private val db by lazy {
        FirebaseDatabase.getInstance("https://smart-iv-hackon-patientid.firebaseio.com/")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_entry)

        btnPatID.setOnClickListener {
            val patID = etPatientID.editText?.text.toString()

            val myRef = db.reference
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    btnPatID.doResult(false)
                }

                override fun onDataChange(p0: DataSnapshot) {

                }

            })
        }
    }


    override fun onBackPressed() {
        val backIntent = Intent(this, HomeActivity::class.java)
        startActivity(backIntent)
    }
}
