package com.example.dailykhabar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_category.*

class category : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        sportstxt.setOnClickListener{
            val intent = Intent (this,sports::class.java)
            startActivity(intent)
        }

        enterttxt.setOnClickListener{
            val intent1 = Intent(this,entertainment::class.java)
            startActivity(intent1)
        }

        healthtxt.setOnClickListener{
            val intent2 = Intent (this, health::class.java)
            startActivity(intent2)
        }

        buisnesstxt.setOnClickListener{
            val intent3 =   Intent(this, Buisness::class.java)
            startActivity(intent3)
        }
        techonolgytxt.setOnClickListener{
            val intent4  = Intent(this,technology::class.java)
            startActivity(intent4)
        }


    }
}
