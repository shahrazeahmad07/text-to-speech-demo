package com.example.texttospeechdemo

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.texttospeechdemo.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding: ActivityMainBinding? = null
    private var textToSpeech: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // setting initializing text to speech
        textToSpeech = TextToSpeech(this, this)

        // speak button
        binding?.btnSpeak?.setOnClickListener {
            if (binding?.tilText?.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter Text First!!", Toast.LENGTH_SHORT).show()
            } else {
                // checking if currently speaking or not and change process otherwise
                if (textToSpeech!!.isSpeaking) {
                    textToSpeech?.stop()
                    binding?.btnSpeak?.text = getString(R.string.speak)
                }
                else {
                    speak(binding?.tilText?.text.toString())
                }
            }
        }
    }

    //! Speak Method, responsible for the mobile to speak.
    private fun speak(text: String) {
        textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    // onInit block, called when tts object is initialzed
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA) {
                Toast.makeText(this@MainActivity, "Language not Found", Toast.LENGTH_SHORT).show()
            }

            // checking timer progress, and changing button text accordingly
            textToSpeech?.setOnUtteranceProgressListener(object: UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {
                    binding?.btnSpeak?.text = getString(R.string.stop)
                }

                override fun onDone(utteranceId: String?) {
                    binding?.btnSpeak?.text = getString(R.string.speak)
                }

                override fun onError(utteranceId: String?) {
                    // do Nothing
                }

            })
        } else {
            Toast.makeText(this@MainActivity, "Initialization Failed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (textToSpeech != null) {
            textToSpeech?.stop()
            textToSpeech?.shutdown()
            textToSpeech = null
        }
        if (binding != null) {
            binding = null
        }
    }
}