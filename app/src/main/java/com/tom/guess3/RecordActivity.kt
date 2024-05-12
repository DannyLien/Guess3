package com.tom.guess3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tom.guess3.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val count = intent.getIntExtra("COUNTER", -1)
        binding.counter.setText(count.toString())

        binding.save.setOnClickListener {
            val nickName = binding.nickName.text.toString()
            getSharedPreferences("guess", MODE_PRIVATE)
                .edit()
                .putInt("REC_COUNTER", count)
                .putString("REC_NICKNAME", nickName)
                .apply()
            finish()
        }

    }
}