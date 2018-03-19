package application.mike

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import application.mike.R.layout.activity_register
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.result.Result
import com.google.gson.JsonObject
import org.json.JSONObject
import org.xml.sax.Parser

class Login : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        startActivity(Intent(this@Login, UserProfil::class.java))

        val LoginButton = findViewById<Button>(R.id.Login)

        LoginButton.setOnClickListener{
            var login = findViewById<EditText>(R.id.Username)
            var password = findViewById<EditText>(R.id.Password)


            //Toast.makeText(this@Login, "Login", Toast.LENGTH_LONG).show()
            connect(login.text.toString(), password.text.toString())

        }

        val register = findViewById<Button>(R.id.register)

        register.setOnClickListener {
            startActivity(Intent(this@Login, Register::class.java))
        }

    }

    fun connect(name: String, password: String) {
        var accesstoken: Json
        val rootObject= JSONObject()

        rootObject.put("username", name)
        rootObject.put("password", password)

        Fuel.post("https://mike.arrogant.space/v1/login").header(Pair("Content-Type", "application/json")).body(rootObject.toString()).responseJson { request, response, result ->
            when (result) {
                is Result.Success -> {
                    accesstoken = result.get()
                    val jsonObject = accesstoken.obj() //JSONObject
  //                  val jsonArray = accesstoken.parse("access_token") //JSONArray
                    println("Result: ${jsonObject.getString("access_token")}")
                    AccessToken.ACCESS_TOKEN = jsonObject.getString("access_token")
                    startActivity(Intent(this@Login, UserProfil::class.java))
                }
                is Result.Failure -> {
                    Toast.makeText(this@Login, "Error Login", Toast.LENGTH_LONG).show()
                    println("Fail déso gros")
                }
            }
        }
    }

}