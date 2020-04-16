package com.codeslayers.smartiv.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.codeslayers.smartiv.R
import com.codeslayers.smartiv.createentry.CreateEntryActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    private val currentUser by lazy {
        auth.currentUser
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        cvNewEntry.setOnClickListener {
            val newEntryIntent = Intent(
                this,
                CreateEntryActivity::class.java
            )
            startActivity(newEntryIntent)
        }

        cvCheckPatience.setOnClickListener {
            Snackbar.make(rootLayout, "In Progress", Snackbar.LENGTH_SHORT).show()
        }

        cvDeleteDrip.setOnClickListener {
            Snackbar.make(rootLayout, "In Progress", Snackbar.LENGTH_SHORT).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_signout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.btnSignout -> {
            auth.signOut()
            Toast.makeText(this, "Sign Out Successful", Toast.LENGTH_SHORT).show()
            val signOutIntent = Intent(this, LoginActivity::class.java)
            startActivity(signOutIntent)
            true
        }


        else -> super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}
