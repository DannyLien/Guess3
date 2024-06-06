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

    private val REQUEST_RECORD = 100
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMaterialBinding
    val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        Log.d(TAG, "onCreate: ")
        Log.d(TAG, "onCreate: secret = ${secretNumber.secret} ")

        binding.fab.setOnClickListener { view ->
            replay()
        }
        binding.contentView.counter.setText(secretNumber.count.toString())
        Log.d(TAG, "onCreate: secret = ${secretNumber.secret} ")
        val count = getSharedPreferences("guess", MODE_PRIVATE).getInt("REC_COUNTER", -1)
        val nick = getSharedPreferences("guess", MODE_PRIVATE).getString("REC_NICKNAME", null)
        Log.d(TAG, "onCreate: getSharedPreferences data : ${count} , ${nick}")

    }

    private fun replay() {
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

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
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
            .setPositiveButton(getString(R.string.ok), { dialog, which ->
                if (diff == 0) {
                    val intent = Intent(this, RecordActivity::class.java)
                    intent.putExtra("COUNTER", secretNumber.count)
//                    startActivity(intent)
                    startActivityForResult(intent, REQUEST_RECORD)
                }
            })
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            val nickname = data?.getStringExtra("NICK")
            Log.d(TAG, "onActivityResult: $nickname")
            replay()
        }
    }
}