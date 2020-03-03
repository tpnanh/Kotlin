package com.example.aboutme

import android.opengl.ETC1
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDone = findViewById<Button>(R.id.btn_done)

        btnDone.setOnClickListener(View.OnClickListener {
            updateName(it)
            btnDone.visibility = View.GONE
        })
    }

    private fun updateName(view: View){
        val enterName = findViewById<EditText>(R.id.enter)
        val name = findViewById<TextView>(R.id.name)

        name.text = enterName.text
        name.visibility = View.VISIBLE
        enterName.visibility = View.GONE

    }
}
