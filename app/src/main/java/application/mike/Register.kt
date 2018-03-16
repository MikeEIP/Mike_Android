package application.mike

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.Size
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import org.intellij.lang.annotations.Pattern
import org.jetbrains.annotations.NotNull
import org.json.JSONObject

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val RegisterButton = findViewById<Button>(R.id.RegisterButton)

        RegisterButton.setOnClickListener {
            UserRegister()
        }
    }

    fun UserRegister() {
        var email = findViewById<EditText>(R.id.Email) as EditText
        var username = findViewById<EditText>(R.id.Username) as EditText
        var password = findViewById<EditText>(R.id.Password) as EditText
        var passwordcnmfirm = findViewById<EditText>(R.id.PasswordConfirm) as EditText
        val btn = findViewById<Button>(R.id.RegisterButton) as Button

        if (email.text.isEmpty() && username.text.isEmpty() && password.text.isEmpty()) {
            Toast.makeText(this, "No login or password provided", Toast.LENGTH_SHORT).show()
            return
        }
        if (!Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$").containsMatchIn(email.text)){
            Toast.makeText(this, "You must provide a correct Email", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.text != passwordcnmfirm.text){
            Toast.makeText(this, "Password missmatch", Toast.LENGTH_SHORT).show()
            return
        }

        val rootObject= JSONObject()
        rootObject.put("password", password.text.toString())
        rootObject.put("username", username.text.toString())
        rootObject.put("lastname", "dupont")
        rootObject.put("firstname", "mike")
        rootObject.put("email", email.text.toString())
        rootObject.put("birthday", "1992-11-11T08:40:51.620Z")
        rootObject.put("language", "fr_FR")
        rootObject.put("xp", 20)
        rootObject.put("musclor", 10)

        println(rootObject?.toString())
        Fuel.post("https://mike.arrogant.space/v1/user").header(Pair("Content-Type", "application/json")).body(rootObject.toString()).responseString { request, response, result ->
            when (result) {
                is Result.Success -> {
                    startActivity(Intent(this@Register, UserProfil::class.java))
                    println("Result: ${result.get()}")
                }
                is Result.Failure -> {
                    Toast.makeText(this@Register, "Error Login", Toast.LENGTH_LONG).show()
                    println("Fail déso gros")
                }
            }
        }
    }

}