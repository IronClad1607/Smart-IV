package com.codeslayers.smartiv.createentry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    var patName: String = ""
    var patGen: String = ""
    var patPN: String = ""
    var patAge: String = ""
    var patBG: String = ""
    var patEn: String = ""
    var patDoc: String = ""
    var patSym: String = ""
    var roomNumber: String = ""
    var bedNumber: String = ""


    private val db by lazy {
        FirebaseDatabase.getInstance("https://smart-iv-hackon-patientid.firebaseio.com/")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_entry)

        btnPatID.setOnClickListener {
            val patID = etPatientID.editText?.text.toString()

            GlobalScope.launch(Dispatchers.Main) {
                val myRef = db.reference
                myRef.addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {
                        btnPatID.doResult(false)
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        showData(p0, patID)
                        runOnUiThread {
                            btnPatID.doResult(true)
                        }


                        Log.d(
                            "PUI", """
                        $patName
                        $patGen
                        $patPN
                    """.trimIndent()
                        )
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
                patBG = ds.child("patientBloodGroup").value.toString()
            }
        }
    }


    override fun onBackPressed() {
        val backIntent = Intent(this, HomeActivity::class.java)
        startActivity(backIntent)
    }
}
