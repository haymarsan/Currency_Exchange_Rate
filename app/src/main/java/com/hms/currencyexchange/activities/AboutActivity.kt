package com.hms.currencyexchange.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.hms.currencyexchange.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    val TAG = javaClass.simpleName

    companion  object{
        fun newInstance(context: Context):Intent{
            return Intent(context, AboutActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        btnCheckUpdate.setOnClickListener {

            openMarket(getString(R.string.app_url), getString(R.string.app_package))
        }

    }

    private fun openMarket(url: String, appPackage: String) {
        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackage))
        intent.data = Uri.parse(url)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        ContextCompat.startActivity(this, intent, null)
    }
}
