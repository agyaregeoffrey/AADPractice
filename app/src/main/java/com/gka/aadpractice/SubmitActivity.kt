package com.gka.aadpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.gka.aadpractice.services.EndPoints
import com.gka.aadpractice.services.ServiceBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_submit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "SubmitActivity"

class SubmitActivity : AppCompatActivity(), AlertFragment.OnRequestConfirmationListener {

    private lateinit var firstName: String
    private lateinit var lastName: String
    private lateinit var emailAddress: String
    private lateinit var gitHubLink: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)

        backButton.setOnClickListener { onBackPressed() }

        buttonSubmission.setOnClickListener {
            confirm()
        }
    }

    // executes when buttonYes is clicked
    override fun onConfirmed() {
        processForm()
    }

    // checks form validity
    private fun confirm() {
        firstName = editTextFirstName.text.toString().trim()
        lastName = editTextLastName.text.toString().trim()
        emailAddress = editTextEmailAddress.text.toString().trim()
        gitHubLink = editTextGithubLink.text.toString().trim()

        var formInputValidity = true

        if (firstName.isEmpty() || lastName.isEmpty() || emailAddress.isEmpty() || gitHubLink.isEmpty()) {
            showMessage("All Fields Required")
            formInputValidity = false
        }

        if (formInputValidity) {
            AlertFragment.newInstance("confirmation").show(supportFragmentManager, "")
        }
    }

    private fun processForm() {
        showProgress(true)
        val formService = ServiceBuilder.googleFormApiInstance(EndPoints::class.java)
        formService.submitFormData(firstName, lastName, emailAddress, gitHubLink)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Log.i(TAG, "${response.code()}")
                        showProgress(false)
                        AlertFragment.newInstance("success").show(supportFragmentManager, "")
                        resetForm()
                    }else {
                        Log.i(TAG, "${response.code()}")
                        showProgress(false)
                        AlertFragment.newInstance("failure").show(supportFragmentManager, "")
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(applicationContext, "Message: ${t.message}", Toast.LENGTH_LONG)
                        .show()
                    showProgress(false)
                    AlertFragment.newInstance("failure").show(supportFragmentManager, "")
                }

            })
    }

    private fun showMessage(message: String) {
        Snackbar.make(buttonSubmission, message, Snackbar.LENGTH_LONG).show()
    }

    private fun resetForm() {
        editTextFirstName.setText("")
        editTextLastName.setText("")
        editTextEmailAddress.setText("")
        editTextGithubLink.setText("")
    }

    private fun showProgress(state: Boolean) {
        groupSubmitProject.isVisible = state
    }
}