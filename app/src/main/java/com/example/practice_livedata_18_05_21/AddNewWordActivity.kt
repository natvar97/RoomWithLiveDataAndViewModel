package com.example.practice_livedata_18_05_21

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practice_livedata_18_05_21.ui.MainActivity

class AddNewWordActivity : AppCompatActivity() {

     val EXTRA_REPLY = "EXTRA_REPLY"
    private lateinit var editText: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_word)

        editText = findViewById(R.id.edit_word)
        button = findViewById(R.id.button_save)

        button.setOnClickListener {
            if (!TextUtils.isEmpty(editText.text.toString().trim { it <= ' ' })) {
                Toast.makeText(this, "Word should be provided", Toast.LENGTH_SHORT).show()
            } else {
                val word = editText.text.toString().trim()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(EXTRA_REPLY, word)
                startActivity(intent)
            }
        }

    }
}