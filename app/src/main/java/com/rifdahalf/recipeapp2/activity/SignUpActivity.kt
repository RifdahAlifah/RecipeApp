package com.rifdahalf.recipeapp2.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.rifdahalf.recipeapp2.R
import com.rifdahalf.recipeapp2.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var  signUpBinding: ActivitySignUpBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var  refUsers : DatabaseReference
    private  var  firebaseUid : String = ""

    companion object{
        fun getLaunchService (from: Context) = Intent(from, SignUpActivity::class.java).apply{
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(signUpBinding.root)
        supportActionBar?.hide()

        signUpBinding.btnSignUp.setOnClickListener(this)
        signUpBinding.tvSignIn.setOnClickListener(this)
        signUpBinding.ivBack.setOnClickListener(this)
        mAuth = FirebaseAuth.getInstance()

        //get instance  untuk memanggil semua function dari si firebase
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.btn_sign_up -> signUpUser()
            R.id.tv_sign_in -> startActivity(Intent(SignInActivity.getLaunchService(this)))
            R.id.iv_back -> startActivity(Intent(SignInActivity.getLaunchService(this)))
        }
    }

    private fun signUpUser() {
        val username : String = signUpBinding.etUsernameSignUp.text.toString()
        val email : String = signUpBinding.etEmailSignUp.text.toString()
        val password : String = signUpBinding.etPasswordSignUp.text.toString()
        if (username == "") {
            Toast.makeText(this,getString(R.string.not_empty), Toast.LENGTH_SHORT).show()
        }else if (email.isEmpty()) {
            Toast.makeText(this,getString(R.string.not_empty), Toast.LENGTH_SHORT).show()
        }else if (password.isEmpty()){
            Toast.makeText(this,getString(R.string.not_empty), Toast.LENGTH_SHORT).show()
        }else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { it ->
                if(it.isSuccessful)  {
                    firebaseUid = mAuth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUid)
                    val userHashMap = HashMap<String, Any>()
                    userHashMap["uid"] = firebaseUid
                    userHashMap["name"] = username
                    userHashMap["email"] = email
                    userHashMap["photo"] = ""
                    userHashMap["linkedIn"] = ""
                    userHashMap["instagram"] = ""

                    refUsers.updateChildren(userHashMap).addOnCompleteListener {
                        if(it.isSuccessful){
                            startActivity(Intent(MainActivity.getLaunchService(this)))
                            finish()
                        }
                    }
                }else{
                    val progress = ProgressDialog(this, R.style.Theme_AppCompat_Light_Dialog)
                    progress.hide()
                    Toast.makeText(this, "Register Failed " + it.exception!!
                        .message.toString(), Toast.LENGTH_SHORT).show()
                    finish()
                }

            }
        }

    }
}