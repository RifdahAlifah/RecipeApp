package com.rifdahalf.recipeapp2.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.rifdahalf.recipeapp2.R
import com.rifdahalf.recipeapp2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainActivityMainBinding: ActivityMainBinding
    companion object{
        fun getLaunchService(from : Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityMainBinding.root)
        supportActionBar?.hide()

        mainActivityMainBinding.btnLogOut.setOnClickListener(this)


    }

    private fun signOUt(){
        startActivity(Intent(SignInActivity.getLaunchService(this)))
        FirebaseAuth.getInstance().signOut()
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_log_out -> signOUt()
        }

    }
}