package application.mike

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import org.json.JSONObject

class UserProfil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profil)

        val URL : String = "https://mike.arrogant.space/v1/user/me"
        var profile: Json

        URL.httpGet().header(Pair("Authorization", "Bearer ${AccessToken.ACCESS_TOKEN}")).responseJson()
        { request, response, result ->
            when (result) {
                is Result.Success -> {
                    profile = result.get()
                    val profileObject = profile.obj()
                    println("Result: ${profile}")
                    var username = profileObject.getString("username")
                    var lastname = profileObject.getString("lastname")
                    var firstname = profileObject.getString("firstname")
                    var email = profileObject.getString("email")
                    var birthday = profileObject.getString("birthday")
                    var language = profileObject.getString("language")
                    val xp = profileObject.getInt("xp")
                    val musclor = profileObject.getString("musclor")
                }
                is Result.Failure -> {
                    Toast.makeText(this@UserProfil, "Check your connexion", Toast.LENGTH_LONG).show()
                }
            }
        }
        println(AccessToken.ACCESS_TOKEN)
    }

}
