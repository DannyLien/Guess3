package com.tom.guess3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.tom.guess3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val TAG = MainActivity::class.java.simpleName
    val secretNumber = SecretNumber()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate: secret = ${secretNumber.secret} ")


    }

    fun check(view: View) {
//        findViewById<TextView>(R.id.number)
        val n = binding.number.text.toString().toInt()
        Log.d(TAG, "check: number ${n}")
        var message = "You got it!"
        if (secretNumber.validate(n) < 0) {
            message = "Bigger"
        } else if (secretNumber.validate(n) > 0) {
            message = "Smaller"
        }
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
            .setTitle("Message")
            .setMessage(message)
            .setPositiveButton("OK",null)
            .show()
    }


}