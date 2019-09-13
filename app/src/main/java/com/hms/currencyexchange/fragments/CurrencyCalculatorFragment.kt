import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hms.currencyexchange.R
import com.hms.currencyexchange.data.room.CurrencyEntity
import com.hms.currencyexchange.data.vos.RateVO
import com.hms.currencyexchange.viewmodel.ExchangeRateViewModel
import com.hms.currencyexchange.viewmodel.ExchangeRateViewModelImpl
import kotlinx.android.synthetic.main.fragment_currency_calculation.*
import kotlinx.android.synthetic.main.fragment_currency_calculation.view.*
import kotlin.NumberFormatException

class CurrencyCalculatorFragment : Fragment() {

    lateinit var mViewModel: ExchangeRateViewModel

    private var currencyType = "USD"
    var stateCurrency = 0.0
    val valueCurrency = 0.0


    private lateinit var mSpinner: Spinner

    private lateinit var mAmount: EditText

    private lateinit var mMMK: EditText

    var mAmountString = ""
    //var mMMK =  ""

    var currencyList = ArrayList<RateVO>()

    var usdRate = 0.0
    var sgdRate = 0.0
    var eurRate = 0.0
    var thbRate = 0.0
    var jpyRate = 0.0


    companion object {
        fun newFragment(): CurrencyCalculatorFragment {
            return CurrencyCalculatorFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_currency_calculation, container, false)
        mSpinner = view.findViewById(R.id.spnCurrency) as Spinner
        mAmount = view.findViewById(R.id.etAmount) as EditText
        mMMK = view.findViewById(R.id.etMMK) as EditText

        //mAmountString = mAmount.toString()

        //val currencyList = arrayOf("USD", "EUR", "SGD", "THB")

        mViewModel = ViewModelProviders.of(this).get(ExchangeRateViewModelImpl::class.java)

        mSpinner.isEnabled = false
        view.progressCalculate.visibility = View.VISIBLE

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var allList = ArrayList<CurrencyEntity>()
        mViewModel.getAllCurrency().observe(this, Observer {


            // allList = data as ArrayList<CurrencyEntity>
            view.progressCalculate.visibility = View.GONE
            mSpinner.isEnabled = true
            for (currency in it) {
                val v = currency.mmk.replace(",", "")
                if (isFavouriteCurrency(currency.currency_code, v.toDouble()))
                    currencyList.add(RateVO(currency.currency_code, v))

            }
            Log.d("usd rate", usdRate.toString())

            mAmount.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val rate = s.toString().toDouble()
                    currencyTypeGenerator()
                }

                override fun afterTextChanged(s: Editable?) {

                }

            })

        })

        mSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                currencyType = mSpinner.selectedItem.toString()
                Log.d("current", currencyType)
                currencyTypeGenerator()


            }

        }
    }


    private fun currencyTypeGenerator() {
        try {
            when (currencyType) {
                "USD" -> {
                    if (!mAmount.text.toString().isNullOrBlank()) {
                        mMMK.setText(
                            calculateCurrency(mAmount.text.toString().toDouble(), usdRate).toString()
                        )
                    } else {
                        mMMK.setText("0.0")
                    }
                }
                "EUR" -> {
                    if (!mAmount.text.toString().isNullOrBlank()) {
                        mMMK.setText(
                            calculateCurrency(mAmount.text.toString().toDouble(), eurRate).toString()
                        )
                    }else{
                        mMMK.setText("0.0")
                    }
                }
                "SGD" -> {

                    if (!mAmount.text.toString().isNullOrBlank()) {
                        mMMK.setText(

                            calculateCurrency(mAmount.text.toString().toDouble(), sgdRate).toString()
                        )
                    }else{
                        mMMK.setText("0.0")
                    }
                }
                "THB" -> {
                    if (!mAmount.text.toString().isNullOrBlank()) {
                        mMMK.setText(

                            calculateCurrency(mAmount.text.toString().toDouble(), thbRate).toString()
                        )
                    }else{
                        mMMK.setText("0.0")
                    }
                }
                "JPY" -> {
                    if (!mAmount.text.toString().isNullOrBlank()) {
                        mMMK.setText(
                            calculateCurrency(mAmount.text.toString().toDouble(), jpyRate).toString()
                        )
                    }else{
                        mMMK.setText("0.0")
                    }
                }
            }
        } catch (e: java.lang.NumberFormatException) {
            e.printStackTrace()
        }


    }

    private fun calculateCurrency(amount: Double, rate: Double): Double = amount * rate


    private fun isFavouriteCurrency(currency: String, value: Double): Boolean {
        var isCurrency = false

        when (currency) {
            "USD" -> {
                usdRate = value
                isCurrency = true
            }
            "EUR" -> {
                eurRate = value
                isCurrency = true
            }
            "SGD" -> {
                sgdRate = value
                isCurrency = true
            }
            "THB" -> {
                thbRate = value
                isCurrency = true
            }
            "JPY" -> {
                jpyRate = value
                isCurrency = true
            }
            else -> isCurrency = false

        }

        return isCurrency


    }


}