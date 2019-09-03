package com.hms.currencyexchange.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hms.currencyexchange.R
import com.hms.currencyexchange.adapters.ExchangeRateAdapter
import com.hms.currencyexchange.data.room.CurrencyEntity
import com.hms.currencyexchange.data.vos.RateVO
import com.hms.currencyexchange.viewmodel.ExchangeRateViewModel
import com.hms.currencyexchange.viewmodel.ExchangeRateViewModelImpl
import kotlinx.android.synthetic.main.fragment_all_currency.*
import kotlinx.android.synthetic.main.fragment_all_currency.view.*

class AllCurrencyFragment : Fragment() {

    lateinit var mViewModel: ExchangeRateViewModel
    lateinit var mAdapter: ExchangeRateAdapter

    companion object {
        fun newFragment(): AllCurrencyFragment {


            return AllCurrencyFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_all_currency, container, false)

        mViewModel = ViewModelProviders.of(this).get(ExchangeRateViewModelImpl::class.java)

        mAdapter = ExchangeRateAdapter(context!!)

        view.recyclerExchange.layoutManager = LinearLayoutManager(context)
        view.recyclerExchange.adapter = mAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var curList = ArrayList<CurrencyEntity>()
        mViewModel.getAllCurrency().observe(this, Observer {
            curList = it as ArrayList<CurrencyEntity>
            curList.add(CurrencyEntity(0, "",""))
            mAdapter.setNewData(curList)
        })

    }

    }















//          mViewModel = ViewModelProviders.of(this).get(ExchangeRateViewModelImpl::class.java)
//        mAdapter = ExchangeRateAdapter(applicationContext)
//
//        recyclerExchange.layoutManager = LinearLayoutManager(this)
//        recyclerExchange.adapter = mAdapter
//
//
//        progress.visibility = View.VISIBLE
//        mViewModel.getExchangeRate().observe(this, Observer {
//            val data = it
//
//            progress.visibility = View.GONE
//            Log.d("Data Set", data.description)
//
//            var currencyList = ArrayList<RateVO>()
//
//            for ((key, value)in it.rates){
//                currencyList.add(RateVO(key, value))
//
//            }
//
//            mAdapter.setNewData(currencyList as List<RateVO>)
//        })