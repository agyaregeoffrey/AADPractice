package com.gka.aadpractice

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_alert.*
import kotlinx.android.synthetic.main.fragment_alert.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [AlertFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val ARG_PARAM_TYPE = "alert_type"

class AlertFragment : DialogFragment() {

    private var mAlertType: String? = null

    private lateinit var listener: OnRequestConfirmationListener

    interface OnRequestConfirmationListener {
        fun onConfirmed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            mAlertType = bundle.getString(ARG_PARAM_TYPE)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val root = LayoutInflater.from(context)
            .inflate(R.layout.fragment_alert, null, false)

        return activity?.let { fragmentActivity ->
            val builder = AlertDialog.Builder(fragmentActivity)

            val alertType = mAlertType

            if (alertType == "success") {
                root.groupSure.isVisible = false
                root.imageViewStatus.setImageResource(R.drawable.ic_baseline_check_circle_24)
                root.textViewStatus.text = getString(R.string.label_submission_successful)
                root.imageButtonCancel.isVisible = false
                root.groupStatus.isVisible = true

            } else if (alertType == "failure") {
                root.groupSure.isVisible = false
                root.imageViewStatus.setImageResource(R.drawable.ic_baseline_warning_24)
                root.textViewStatus.text = getString(R.string.label_submission_failed)
                root.imageButtonCancel.isVisible = false
                root.groupStatus.isVisible = true
            }

            root.imageButtonCancel.setOnClickListener { dismiss() }
            root.buttonYes.setOnClickListener {
                listener.onConfirmed()
                dismiss()
            }
            builder.setView(root)
            builder.setCancelable(mAlertType != "confirmation")
            builder.create()
        }?: throw IllegalStateException("Parent cannot be null")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = activity as OnRequestConfirmationListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                (context.toString() +
                        " must implement OnRequestConfirmationListener")
            )
        }
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(alertType: String) =
            AlertFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TYPE, alertType)
                }
            }

    }
}