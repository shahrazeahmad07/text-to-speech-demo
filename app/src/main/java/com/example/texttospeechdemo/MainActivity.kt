package com.example.texttospeechdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import com.example.texttospeechdemo.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding: ActivityMainBinding? = null
    private var textToSpeech: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        textToSpeech = TextToSpeech(this, this)


        binding?.btnSpeak?.setOnClickListener {
            if (binding?.tilText?.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter Text First!!", Toast.LENGTH_SHORT).show()
            } else {
                speak(binding?.tilText?.text.toString())
            }
        }
    }

    private fun speak(text: String) {
        textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA) {
                Toast.makeText(this@MainActivity, "Language not Found", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this@MainActivity, "Initialization Failed", Toast.LENGTH_SHORT).show()
        }
    }
}