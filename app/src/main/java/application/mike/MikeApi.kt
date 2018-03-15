package application.mike

import android.app.ProgressDialog
import android.view.View
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

/**
 * Created by august_t on 14/03/2018.
 */

fun MikeLogin() {
    val URL = "https://mike.arrogant.space/v1/login"
/*    URL.httpPost().responseString { request, response, result ->
        when(result) {
            is Result.Success -> {println("Result: ${result.get()}")}
            is Result.Failure -> {}
        }
    }*/

    val rootObject= JSONObject()
    rootObject.put("username","theSnoop")
    rootObject.put("password","thePass")

    Fuel.post("https://mike.arrogant.space/v1/login").body("""[{"username": "theSnoop", "password": "thePass"}]""").response { request, response, result ->
        when (result) {
            is Result.Success -> {
                println("Result: ${result.get()}")
            }
            is Result.Failure -> {
                println("Fail déso gros")
            }
        }
    }
}
