package com.anything.railfence

// MainActivity.kt
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.anything.railfence.R


class MainActivity : AppCompatActivity() {

    private lateinit var editTextPlainText: EditText
    private lateinit var editTextDepth: EditText
    private lateinit var buttonEncrypt: Button
    private lateinit var buttonDecrypt: Button
    private lateinit var textViewEncryptedResult: TextView
    private lateinit var textViewDecryptedResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextPlainText = findViewById(R.id.editTextPlainText)
        editTextDepth = findViewById(R.id.editTextDepth)
        buttonEncrypt = findViewById(R.id.buttonEncrypt)
        buttonDecrypt = findViewById(R.id.buttonDecrypt)
        textViewEncryptedResult = findViewById(R.id.textViewEncryptedResult)
        textViewDecryptedResult = findViewById(R.id.textViewDecryptedResult)

        buttonEncrypt.setOnClickListener {
            val plainText = editTextPlainText.text.toString()
            val depth = editTextDepth.text.toString().toIntOrNull() ?: 1

            val encryptedText = railFenceEncrypt(plainText, depth)
            textViewEncryptedResult.text = encryptedText
        }

        buttonDecrypt.setOnClickListener {
            val encryptedText = textViewEncryptedResult.text.toString()
            val depth = editTextDepth.text.toString().toIntOrNull() ?: 1

            val decryptedText = railFenceDecrypt(encryptedText, depth)
            textViewDecryptedResult.text = decryptedText
        }
    }

    private fun railFenceEncrypt(plainText: String, depth: Int): String {
        // Implement rail fence encryption algorithm
        // This is a simplified example, you may need to adjust it based on your specific algorithm

        val rails = Array(depth) { StringBuilder() }
        var currentRail = 0
        var goingDown = false

        for (char in plainText) {
            rails[currentRail].append(char)

            if (currentRail == 0 || currentRail == depth - 1) {
                goingDown = !goingDown
            }

            currentRail += if (goingDown) 1 else -1
        }

        return rails.joinToString("")
    }

    private fun railFenceDecrypt(encryptedText: String, depth: Int): String {
        // Implement rail fence decryption algorithm
        // This is a simplified example, you may need to adjust it based on your specific algorithm

        val decoded = CharArray(encryptedText.length)
        var idx = 0
        val n = encryptedText.length

        for (row in 0 until depth) {
            var i = row
            var step = 2 * (depth - row - 1)

            while (i < n) {
                decoded[i] = encryptedText[idx++]
                if (step != 0 && step != 2 * (depth - 1)) {
                    i += step
                    step = 2 * (depth - 1) - step
                } else {
                    i += 2 * (depth - 1)
                }
            }
        }

        return String(decoded)
    }
}
