package com.rifdahalf.recipeapp2.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.rifdahalf.recipeapp2.R
import com.rifdahalf.recipeapp2.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var  signInBinding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    companion object {
        fun getLaunchService (from:Context) = Intent(from, SignInActivity::class.java).apply{
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signInBinding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(signInBinding.root)
        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()

        signInBinding.tvForgotPw.setOnClickListener(this)
        signInBinding.btnSignin.setOnClickListener(this)
        signInBinding.tvSignup.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_signin -> signIn()
            R.id.tv_signup -> startActivity(Intent(SignUpActivity.getLaunchService(this)))
            R.id.tv_forgot_pw-> startActivity(Intent(ForgotActivity.getLaunchService(this)))
        }
    }

    // ini untuk kalau semisal udah sign in jadi bakal ke main activity gak ke sign in lagi
    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null){
            startActivity(MainActivity.getLaunchService(this))
        }
    }

    private fun signIn() {
        val email = signInBinding.etEmailSignin.text.toString()
        val password = signInBinding.etPwSignin.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please insert complete data", Toast.LENGTH_SHORT).show()
        }

        //addOnCompleteListener = fungsinya untuk menambahkan antrian
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this, "Login Succes", Toast.LENGTH_SHORT).show()
                startActivity(MainActivity.getLaunchService(this))
                return@addOnCompleteListener
            }else{
                Toast.makeText(this,"Login Gagal", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            val progress = ProgressDialog(this, R.style.Theme_AppCompat_Light_Dialog)
            progress.hide()
            Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show()
        }
    }
}