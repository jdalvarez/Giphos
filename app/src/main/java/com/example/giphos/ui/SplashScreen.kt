package com.example.giphos.ui


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.giphos.R
import com.example.giphos.databinding.SplashScreenBinding

class SplashScreen:AppCompatActivity( ){
    lateinit var binding: SplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val topAnimation = AnimationUtils.loadAnimation(this, R.anim.gipho1)
        val bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.giphos2)
        binding.giphoAnim1.startAnimation(topAnimation)
        binding.giphoAnim2.startAnimation(bottomAnimation)


        val splashScreenTimeOut = 4000
        val home = Intent(this@SplashScreen, MainActivity::class.java)
        Handler().postDelayed({
            startActivity(home)
            finish()
        }, splashScreenTimeOut.toLong())
    }

}