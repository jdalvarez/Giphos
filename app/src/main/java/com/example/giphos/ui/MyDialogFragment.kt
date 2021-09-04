package com.example.giphos.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.giphos.R

class MyDialogFragment(val onPositive:() -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            AlertDialog.Builder(it)
                .setTitle(R.string.dialog_Title)
                .setMessage(R.string.dialog_Text)
                .setPositiveButton(R.string.submit) {_, _ -> onPositive() }
                .setNegativeButton(R.string.cancel) { _, _ -> dismiss() }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }



}