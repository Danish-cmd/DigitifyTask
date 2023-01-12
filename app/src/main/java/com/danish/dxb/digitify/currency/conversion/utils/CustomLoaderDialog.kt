package com.danish.dxb.digitify.currency.conversion.utils

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.danish.dxb.digitify.currency.conversion.R


class CustomLoaderDialog {
    companion object {
        fun createProgressDialog(mContext: Context?, cancelable: Boolean): AlertDialog {
            val builder: AlertDialog.Builder = AlertDialog.Builder(mContext!!)
            builder.setCancelable(false) // if you want user to wait for some process to finish,

            builder.setView(R.layout.loader_dialog)
            val dialog = builder.create()
            try {
                dialog.show()
            } catch (e: Exception) {
                Log.d("progress_error", e.message!!)
            }
            return dialog
        }
    }
}