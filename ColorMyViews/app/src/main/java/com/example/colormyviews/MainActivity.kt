package com.example.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    private fun makeColored(view: View){
        when (view.id){
            R.id.box1 -> view.setBackgroundColor(Color.CYAN)
            R.id.box2 -> view.setBackgroundColor(Color.GREEN)
            R.id.box3 -> view.setBackgroundColor(Color.MAGENTA)
            R.id.box4 -> view.setBackgroundColor(Color.GRAY)
            R.id.box5 -> view.setBackgroundColor(Color.RED)
            else -> view.setBackgroundColor(Color.YELLOW)
        }
    }

    private fun setListeners(){
        val box1 = findViewById<TextView>(R.id.box1)
        val box2 = findViewById<TextView>(R.id.box2)
        val box3 = findViewById<TextView>(R.id.box3)
        val box4 = findViewById<TextView>(R.id.box4)
        val box5 = findViewById<TextView>(R.id.box5)

        val rootCLayout = findViewById<View>(R.id.con_layout)

        val clickableViews: List<View> = listOf(box1, box2, box3, box4, box5, rootCLayout)

        for (item in clickableViews){
            item.setOnClickListener{
                makeColored(it)
            }
        }
    }
}
