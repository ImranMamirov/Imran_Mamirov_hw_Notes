package com.example.imran_mamirov_hw_notes.noteApp.ui.fragment.singup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.imran_mamirov_hw_notes.R
import com.example.imran_mamirov_hw_notes.databinding.FragmentSingUpBinding
import com.example.imran_mamirov_hw_notes.noteApp.utils.SharedPreferenceHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth

class SingUpFragment : Fragment() {

    private lateinit var binding: FragmentSingUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var sharedPreferenceHelper: SharedPreferenceHelper

    private val signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account?.idToken)
            } catch (e: ApiException) {
                updateUi(null)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingUpBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        sharedPreferenceHelper = SharedPreferenceHelper(requireContext())

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.id_client_token))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isOnBoardShown = sharedPreferenceHelper.isOnBoardingComplete()
        val currentUser = auth.currentUser

        if (!isOnBoardShown) {
            findNavController().navigate(R.id.singUpFragment)
            sharedPreferenceHelper.setOnBoardingComplete(true)
        } else if (currentUser != null || sharedPreferenceHelper.isSignInComplete()) {
            updateUi(currentUser)
        } else {
            setUpListener()
        }
    }

    private fun setUpListener() {
        binding.btnSignIn.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        signInLauncher.launch(signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                sharedPreferenceHelper.setSignInComplete(true)
                val user = auth.currentUser
                updateUi(user)
            } else {
                updateUi(null)
            }
        }
    }

    private fun updateUi(user: FirebaseUser?) {
        if (user != null) {
            findNavController().navigate(R.id.noteFragment)
        } else {
            Toast.makeText(requireContext(), "Аутентификация не удалась", Toast.LENGTH_LONG).show()
        }
    }
}