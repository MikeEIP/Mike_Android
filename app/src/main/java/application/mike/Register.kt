package application.mike

import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.Size
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import application.mike.Tools.Tools
import application.mike.Login
import kotlinx.android.synthetic.main.activity_login.*
import org.intellij.lang.annotations.Pattern
import org.jetbrains.annotations.NotNull
import org.json.JSONObject
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class Register : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var username : EditText
    private lateinit var password : EditText
    private lateinit var passwordconfirm : EditText
    private lateinit var firstname: EditText
    private lateinit var lastname : EditText
    private var birthdate : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firstname = findViewById(R.id.firstname)
        lastname = findViewById(R.id.lastname)
        email = findViewById(R.id.Email)
        password = findViewById(R.id.Password)
        passwordconfirm = findViewById(R.id.PasswordConfirm)

        val nextButton = findViewById<Button>(R.id.next)

        nextButton.setOnClickListener {
            registerP2()
        }

        val returnLogin = findViewById<TextView>(R.id.link_login)
        returnLogin.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun registerP2() {

        if (email.text.isEmpty() || firstname.text.isEmpty() || lastname.text.isEmpty() || password.text.isEmpty() || passwordconfirm.text.isEmpty()) {
            Toast.makeText(this, "Un des champs est vide", Toast.LENGTH_SHORT).show()
            return
        }
        if (!Tools.isEmailValid(email.text.toString())){
            Toast.makeText(this, "You must provide a correct Email", Toast.LENGTH_SHORT).show()
            return
        }

        if (!Tools.isPasswordValid(password.text.toString())) {
            Toast.makeText(this, "Le mot de passe doit contenir au moins 1 chiffre, 1 majuscule, un characere spécial", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.text.toString() != passwordconfirm.text.toString()){
            Toast.makeText(this, "Password missmatch", Toast.LENGTH_SHORT).show()
            return
        }

        registerP3()
    }

    private fun registerP3() {
        setContentView(R.layout.activity_register2)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        var checkYears : Int = 2018
        var lblDate = findViewById<TextView>(R.id.date)

        lblDate.setOnClickListener {
            val dpd = DatePickerDialog(this, R.style.Theme_AppCompat_DayNight_Dialog ,DatePickerDialog.OnDateSetListener { view, years, monthOfYear, dayOfMonth ->
                // Display Selected date in textbox
                birthdate = ""
                lblDate.setText("" + dayOfMonth + " / " + (monthOfYear + 1) + " / " + years)
                checkYears = years
                birthdate += years
                birthdate += "-"
                birthdate += (monthOfYear + 1)
                birthdate += "-"
                birthdate += dayOfMonth
                birthdate += "T08:40:51.620Z"
            }, year, month, day)
            dpd.show()
        }
        //Page 2
        username = findViewById(R.id.Username)
        val registerButton = findViewById<Button>(R.id.register)

        registerButton.setOnClickListener {
            if (username.text.isEmpty()) {
                Toast.makeText(this, "Champ vide", Toast.LENGTH_LONG).show()
            } else if ((2018 - checkYears) < 16) {
                Toast.makeText(this, "Minimum 15 ans", Toast.LENGTH_SHORT).show()
            } else {
                createAccount()
            }
        }
    }

    private fun createAccount() {

        val rootObject= JSONObject()
        rootObject.put("password", password.text.toString())
        rootObject.put("username", username.text.toString())
        rootObject.put("lastname", firstname.text.toString())
        rootObject.put("firstname", lastname.text.toString())
        rootObject.put("email", email.text.toString())

        //TODO
        rootObject.put("birthday", birthdate)
        rootObject.put("language", "fr_FR")

        //Pas renseigné
        rootObject.put("xp", 0)
        rootObject.put("musclor", 0)
        rootObject.put("profilPicture", "a")
        Fuel.post("https://mike.arrogant.space/v1/user").header(Pair("Content-Type", "application/json")).body(rootObject.toString()).responseString { request, response, result ->
            when (result) {
                is Result.Success -> {
                    Toast.makeText(this, "La requete fonctionne", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@Register, UserProfil::class.java))
                    System.out.println("Result: ${result.get()}")
                }
                is Result.Failure -> {
                    Toast.makeText(this@Register, "Error Login", Toast.LENGTH_LONG).show()
                    System.out.println("Fail déso gros")
                }
            }
        }

    }
}