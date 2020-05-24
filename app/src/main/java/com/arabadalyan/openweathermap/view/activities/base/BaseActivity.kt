package com.arabadalyan.openweathermap.view.activities.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.arabadalyan.openweathermap.R

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var progressBar: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLoading()
    }

    /**
     * initialize loading
     */
    private fun initLoading() {
        progressBar = Dialog(this, R.style.Theme_AppCompat)
        progressBar.setCancelable(false)

        progressBar.setCancelable(false)
        val view: View = layoutInflater.inflate(R.layout.dialog_progress, null)
        progressBar.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressBar.setTitle(null)
        progressBar.setCancelable(true)
        progressBar.setContentView(view)
    }

    /**
     * show loading if not showed already
     */
     fun showLoading() {
        try {
            if (!progressBar.isShowing && !isFinishing) {
                progressBar.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * hide loading if visible
     */
     fun hideLoading() {
        try {
            if (progressBar.isShowing && !isFinishing) {
                progressBar.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}