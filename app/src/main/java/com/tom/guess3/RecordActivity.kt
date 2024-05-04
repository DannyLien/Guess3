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
        binding.recCounter.setText(count.toString())

    }
}