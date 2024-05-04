package com.tom.guess3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.tom.guess3.databinding.ActivityMaterialBinding

class MaterialActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMaterialBinding
    val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        Log.d(TAG, "onCreate: secret = ${secretNumber.secret} ")

        binding.fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle("Replay game")
                .setMessage("Are you sure?")
                .setPositiveButton(getString(R.string.ok), { dialog, which ->
                    secretNumber.reset()
                    binding.contentView.counter.setText(secretNumber.count.toString())
                    binding.contentView.number.setText("")
                })
                .setNeutralButton("Cancel", null)
                .show()

        }
        binding.contentView.counter.setText(secretNumber.count.toString())
        Log.d(TAG, "onCreate: secret = ${secretNumber.secret} ")

    }

    fun check(view: View) {
//        findViewById<TextView>(R.id.number)
        val n = binding.contentView.number.text.toString().toInt()
        Log.d(TAG, "check: number ${n}")
        val diff = secretNumber.validate(n)
        var message = getString(R.string.you_got_it)
        if (diff < 0) {
            message = getString(R.string.bigger)
        } else if (diff > 0) {
            message = getString(R.string.smaller)
        }
        binding.contentView.counter.setText(secretNumber.count.toString())
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.dialog_title), { dialog, which ->
                if (diff == 0) {
                    val intent = Intent(this, RecordActivity::class.java)
                    intent.putExtra("COUNTER", secretNumber.count)
                    startActivity(intent)
                }
            })
            .show()
    }


}