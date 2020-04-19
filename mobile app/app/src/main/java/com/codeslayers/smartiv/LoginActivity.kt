package com.codeslayers.smartiv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private var DELAY: Long = 1000

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val signInIntent = Intent(this, HomeActivity::class.java)
            startActivity(signInIntent)
        }
    }
    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    private val currentUser by lazy {
        FirebaseAuth.getInstance().currentUser
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        if (currentUser != null) {
            val directIntent = Intent(this, HomeActivity::class.java)
            startActivity(directIntent)
        }

        etLoginId.setOnClickListener {
            btnLogin.reset()
        }
        etPass.setOnClickListener {
            btnLogin.reset()
        }

        btnLogin.setOnClickListener {
            val userID = etLoginId.editText?.text.toString()
            val pass = etPass.editText?.text.toString()

            if (userID.isEmpty() or pass.isEmpty()) {
                Toast.makeText(this, "Enter Login ID or Password", Toast.LENGTH_SHORT).show()
            } else {
                checkEmail(userID, pass)
            }
        }

    }

    private fun signIn(userID: String, pass: String) {
        auth.signInWithEmailAndPassword(userID, pass)
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    try {
                        throw it.exception!!
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthInvalidUserException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthUserCollisionException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    }
                    btnLogin.doResult(false)
                }


            }.addOnSuccessListener {
                btnLogin.doResult(true)
                mDelayHandler = Handler()
                mDelayHandler!!.postDelayed(mRunnable, DELAY)
            }.addOnFailureListener {
                btnLogin.doResult(false)
            }
    }

    private fun signUp(userID: String, pass: String) {
        auth.createUserWithEmailAndPassword(userID, pass)
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    try {
                        throw it.exception!!
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthInvalidUserException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthUserCollisionException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    }
                    btnLogin.doResult(false)
                }
            }.addOnSuccessListener {
                btnLogin.doResult(true)
                mDelayHandler = Handler()
                mDelayHandler!!.postDelayed(mRunnable, DELAY)
            }.addOnFailureListener {
                btnLogin.doResult(false)
            }
    }


    private fun checkEmail(email: String, pass: String) {
        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener {
                if (it.result?.signInMethods?.isEmpty()!!) {

                    Log.d("PUI", "signup")
                    signUp(email, pass)
                } else {
                    Log.d("PUI", "signin")
                    signIn(email, pass)
                }
            }
    }

    override fun onBackPressed() {
        finishAffinity()
    }

    override fun onStart() {
        super.onStart()
        btnLogin.reset()
    }
}
