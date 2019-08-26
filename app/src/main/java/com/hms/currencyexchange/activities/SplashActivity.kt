package com.hms.currencyexchange.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.hms.currencyexchange.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


//        Handler().postDelayed(
//            {
//                ivCover.alpha = 0f
//                Picasso.get().load(R.drawable.cover_p2).into(ivCover)
//                ivCover.animate().setDuration(300).alpha(1f).start()
//            },
//            2000
//        )
        Handler().postDelayed(
            {
                startActivity(MainActivity.newInstance(this))
                finish()
            },
            3000
        )
    }






}
