package com.codeslayers.smartiv.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.codeslayers.smartiv.R
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    private val currentUser by lazy {
        FirebaseAuth.getInstance().currentUser
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            val userID = etLoginId.editText?.text.toString()
            val pass = etPass.editText?.text.toString()

            val check = checkEmail(userID)!!
            Log.d("PUI", "email checked, $check")

            if (!check) {
                Log.d("PUI", "signup")
                signUp(userID, pass)
            } else {
                Log.d("PUI", "signin")
                signIn(userID, pass)
            }
        }

    }

    private fun signIn(userID: String, pass: String) {
        auth.signInWithEmailAndPassword(userID, pass)
            .addOnCompleteListener {
                if(!it.isSuccessful){
                    try {
                        throw it.exception!!
                    }catch (e:FirebaseAuthInvalidCredentialsException){
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    }catch (e:FirebaseAuthInvalidUserException){
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e:FirebaseAuthUserCollisionException){
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthWeakPasswordException){
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }.addOnSuccessListener {
                Toast.makeText(this, "Successfully Login", Toast.LENGTH_SHORT).show()
                val signInIntent = Intent(this, HomeActivity::class.java)
                startActivity(signInIntent)
            }.addOnFailureListener {
                Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
            }
    }

    private fun signUp(userID: String, pass: String) {
        auth.createUserWithEmailAndPassword(userID, pass)
            .addOnCompleteListener {
                if(!it.isSuccessful){
                    try {
                        throw it.exception!!
                    }catch (e:FirebaseAuthInvalidCredentialsException){
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    }catch (e:FirebaseAuthInvalidUserException){
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e:FirebaseAuthUserCollisionException){
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    } catch (e: FirebaseAuthWeakPasswordException){
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }.addOnSuccessListener {
                Toast.makeText(this, "Successfully Login", Toast.LENGTH_LONG).show()
                val signUpIntent = Intent(this, HomeActivity::class.java)
                startActivity(signUpIntent)
            }.addOnFailureListener {
                Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
            }
    }


    private fun checkEmail(email: String): Boolean? {
        var isExist: Boolean? = false
        auth.fetchSignInMethodsForEmail(email)
            .addOnCompleteListener {
                isExist = it.result?.signInMethods?.isEmpty()
            }.addOnFailureListener {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
        return isExist
    }
}
