package com.thiagoio.firebaseauth.ui.signin

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.thiagoio.firebaseauth.R

class SigninFragment : Fragment() {



    private lateinit var viewModel: SigninViewModel
    private var REQUEST_CODE = 1001
    private val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build()
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.signin_fragment, container, false)
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            REQUEST_CODE
        )
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE){
            val response = IdpResponse.fromResultIntent(data)


            if(resultCode == Activity.RESULT_OK){
                val fireBaseUser = Firebase.auth.currentUser
                Log.i("FirebaseUiResult", "${fireBaseUser?.email}")
            }else{
                //sign in failed
            }
        }
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SigninViewModel::class.java)
        // TODO: Use the ViewModel
    }

}