package application.mike

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.widget.Button
import android.widget.Toast
import application.mike.R.layout.activity_register

class Login : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val LoginButton = findViewById<Button>(R.id.Login)

        LoginButton.setOnClickListener{
                //Toast.makeText(this@Login, "Login", Toast.LENGTH_LONG).show()
            MikeLogin()
        }

        val register = findViewById<Button>(R.id.register)

        register.setOnClickListener {
            startActivity(Intent(this@Login, Register::class.java))
        }

    }
}