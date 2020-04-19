package com.codeslayers.smartiv.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codeslayers.smartiv.R
import com.codeslayers.smartiv.createentry.CreateEntryActivity
import com.codeslayers.smartiv.montoring.AssistanceActivity
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

        cvCheckAssist.setOnClickListener {
            val checkIntent = Intent(this,AssistanceActivity::class.java)
            startActivity(checkIntent)
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
            signOutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(signOutIntent)
            true
        }


        else -> super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}
