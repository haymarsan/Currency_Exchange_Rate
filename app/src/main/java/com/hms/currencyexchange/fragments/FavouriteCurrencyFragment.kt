
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hms.currencyexchange.R
import com.hms.currencyexchange.adapters.FavouriteExchangeRateAdapter
import com.hms.currencyexchange.data.vos.RateVO
import com.hms.currencyexchange.viewmodel.ExchangeRateViewModel
import com.hms.currencyexchange.viewmodel.ExchangeRateViewModelImpl
import kotlinx.android.synthetic.main.fragment_favourite_currency.view.*

class FavouriteCurrencyFragment : Fragment() {

    lateinit var mViewModel: ExchangeRateViewModel
    lateinit var mAdapter: FavouriteExchangeRateAdapter
    lateinit var currencyImage:ImageView

    companion object{
        fun newFragment() : FavouriteCurrencyFragment{
            return FavouriteCurrencyFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favourite_currency, container, false)


        mViewModel = ViewModelProviders.of(this).get(ExchangeRateViewModelImpl::class.java)

        mAdapter = FavouriteExchangeRateAdapter(context!!)

        view.recyclerFavouriteExchange.layoutManager = LinearLayoutManager(context)
        view.recyclerFavouriteExchange.adapter = mAdapter


        view.progressFavourite.visibility = View.VISIBLE
        mViewModel.getExchangeRate().observe(this, Observer {
            val data = it

            view.progressFavourite.visibility = View.GONE
            Log.d("Data Set", data.description)

            var currencyList = ArrayList<RateVO>()

            for ((key, value) in it.rates) {

                if (isFavouriteCurrency(key))
                currencyList.add(RateVO(key, value))

            }

            mAdapter.setNewData(currencyList as List<RateVO>)

        })

        return view
    }


    private fun isFavouriteCurrency(currency:String):Boolean{
        var isCurrency = false

        when(currency){
            "USD" -> {
                isCurrency = true
            }
            "EUR" -> {
                isCurrency = true
            }
            "SGD" -> {
                isCurrency = true
            }
            "THB" -> {
                isCurrency = true
            }
            else -> isCurrency = false

        }

        return isCurrency


    }

}