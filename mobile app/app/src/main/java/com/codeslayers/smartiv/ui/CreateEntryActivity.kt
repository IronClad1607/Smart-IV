package com.codeslayers.smartiv.ui

import android.content.Intent
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_entry)


    }
}
