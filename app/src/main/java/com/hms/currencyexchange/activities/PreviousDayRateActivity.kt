package com.hms.currencyexchange.activities

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.hms.currencyexchange.R
import com.hms.currencyexchange.adapters.ExchangeRateAdapter
import com.hms.currencyexchange.data.room.CurrencyEntity
import com.hms.currencyexchange.data.vos.RateVO
import com.hms.currencyexchange.viewmodel.ExchangeRateViewModel
import com.hms.currencyexchange.viewmodel.ExchangeRateViewModelImpl
import com.tsongkha.spinnerdatepicker.DatePicker
import com.tsongkha.spinnerdatepicker.DatePickerDialog
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder
import kotlinx.android.synthetic.main.activity_previous_day_rate.*
import kotlinx.android.synthetic.main.fragment_all_currency.view.*
import java.text.SimpleDateFormat
import java.util.*

class PreviousDayRateActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    lateinit var mViewModel: ExchangeRateViewModel
    lateinit var mAdapter: ExchangeRateAdapter

    private var date: String = ""

    private lateinit var mInterstitialAd: InterstitialAd

    companion object{
        fun newInstance(context: Context): Intent {
            return Intent(context, PreviousDayRateActivity::class.java)

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous_day_rate)

        //check internet connection
//        isConnectingToInternet(this@PreviousDayRateActivity)
//
//        if (isConnectingToInternet(this@PreviousDayRateActivity)) {
//            Toast.makeText(applicationContext,
//                "net is there", Toast.LENGTH_LONG).show()
//        } else {
//            Toast.makeText(applicationContext,
//                "no net", Toast.LENGTH_LONG).show()
//        }


        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.loadAd(AdRequest.Builder().build())



        mViewModel = ViewModelProviders.of(this).get(ExchangeRateViewModelImpl::class.java)

        mAdapter = ExchangeRateAdapter(this)

        recyclerExchange.layoutManager = LinearLayoutManager(this)
        recyclerExchange.adapter = mAdapter

//        btnPreviousRate.setOnClickListener {
//            //date = edDate.text.toString()
//            mViewModel.getPreviousExchangeRate(date).observe(this, Observer {
//                val data = it
//
//                Log.d("Previous DataSet", it.description)
//
//            })
//
//        }

        btnPreviousRate.setOnClickListener {
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.")
            }

            showDate(2019, 1, 1, R.style.DatePickerSpinner )

        }




    }

    override fun onDateSet(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val calendar = GregorianCalendar(year, monthOfYear, dayOfMonth)
        date = SimpleDateFormat("dd-MM-yyy").format(calendar.time)
        btnPreviousRate.setText(date)

        progress.visibility = View.VISIBLE
        mViewModel.getPreviousExchangeRate(date).observe(this, Observer {

            val data = it

            progress.visibility = View.GONE
            Log.d("Data Set", data.description)

            var currencyList = ArrayList<RateVO>()

            for ((key, value) in it.rates) {
                currencyList.add(RateVO(key, value))

            }

            mAdapter.setNewData(currencyList as List<CurrencyEntity>)


        })

    }

    private fun showDate(year:Int, monthOfYear: Int, dayOfMonth: Int, spinnerTheme: Int){
        SpinnerDatePickerDialogBuilder()
            .context(this)
            .callback(this)
            .spinnerTheme(spinnerTheme)
            .defaultDate(year, monthOfYear, dayOfMonth)
            .build()
            .show()
    }

//    fun isConnectingToInternet(context: Context): Boolean {
//        val connectivity = context.getSystemService(
//            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (connectivity != null) {
//            val info = connectivity.allNetworkInfo
//            if (info != null)
//                for (i in info)
//                    if (i.state == NetworkInfo.State.CONNECTED) {
//                        return true
//                    }
//        }
//        return false
//    }
}
