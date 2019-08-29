package com.hms.currencyexchange.activities

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hms.currencyexchange.R
import com.hms.currencyexchange.viewmodel.ExchangeRateViewModel
import com.hms.currencyexchange.viewmodel.ExchangeRateViewModelImpl

class SplashActivity : AppCompatActivity() {


    private val PERMISSION_REQUESTS = 1

    private val TAG = "Permission"

    lateinit var mViewModel: ExchangeRateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mViewModel = ViewModelProviders.of(this).get(ExchangeRateViewModelImpl::class.java)

        // request permission
        if (!allPermissionsGranted()) {
            getRuntimePermissions()
        }

        if (isConnectingToInternet(this)) {
            mViewModel.getExchangeRate().observe(this, Observer {
                val data = it



            })

        } else {
            Toast.makeText(
                this,
                "No Internet Connection, Please access to internet", Toast.LENGTH_LONG
            ).show()
            Handler().postDelayed(
                {
                   finish()
                }, 2000
            )
        }


        Handler().postDelayed(
            {
                startActivity(MainActivity.newInstance(this))
                finish()
            },
            2000
        )


    }



    //check permission
    private fun getRequiredPermissions(): Array<String?> {
        return try {
            val info = this.packageManager
                .getPackageInfo(this.packageName, PackageManager.GET_PERMISSIONS)
            val ps = info.requestedPermissions
            if (ps != null && ps.isNotEmpty()) {
                ps
            } else {
                arrayOfNulls(0)
            }
        } catch (e: Exception) {
            arrayOfNulls(0)
        }
    }

    private fun allPermissionsGranted(): Boolean {
        for (permission in getRequiredPermissions()) {
            permission?.let {
                if (!isPermissionGranted(this, it)) {
                    return false
                }
            }
        }
        return true
    }

    private fun getRuntimePermissions() {
        val allNeededPermissions = ArrayList<String>()
        for (permission in getRequiredPermissions()) {
            permission?.let {
                if (!isPermissionGranted(this, it)) {
                    allNeededPermissions.add(permission)
                }
            }
        }

        if (!allNeededPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(
                this, allNeededPermissions.toTypedArray(), PERMISSION_REQUESTS)
        }
    }

    private fun isPermissionGranted(context: Context, permission: String): Boolean {
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission granted: $permission")
            return true
        }
        Log.i(TAG, "Permission NOT granted: $permission")
        return false
    }


    fun isConnectingToInternet(context: Context): Boolean {
        val connectivity = context.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            if (info != null)
                for (i in info)
                    if (i.state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
        }
        return false
    }


}
