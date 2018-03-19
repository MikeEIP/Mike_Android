package application.mike

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import android.widget.FrameLayout
import application.mike.fragments.TrainingFragment
import application.mike.fragments.ProfilFragment
import application.mike.fragments.FilFragment
import application.mike.fragments.InfosFragment
import application.mike.fragments.LocalisationFragment
import kotlinx.android.synthetic.main.activity_user_profil.*
import android.widget.Toast
import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import org.json.JSONObject


class UserProfil : AppCompatActivity() {

    private var content: FrameLayout? = null

    lateinit var navigationView : BottomNavigationView

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

        content = findViewById(R.id.content) as FrameLayout
        val navigation = findViewById(R.id.bottom_nav_bar) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val fragment = TrainingFragment.Companion.newInstance()
        addFragment(fragment)
    }

    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {

        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.category_Training -> {

                    val fragment = TrainingFragment.Companion.newInstance()
                    addFragment(fragment)

                    return true
                }
                R.id.category_profil -> {
                    val fragment = ProfilFragment()
                    addFragment(fragment)
                    return true
                }
                R.id.category_Fil -> {
                    val fragment = FilFragment()
                    addFragment(fragment)
                    return true
                }
                R.id.category_infos -> {
                    val fragment = InfosFragment()
                    addFragment(fragment)
                    return true
                }
                R.id.category_localisation -> {
                    val fragment = LocalisationFragment()
                    addFragment(fragment)
                    return true
                }
            }
            return false
        }

    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
                .addToBackStack(fragment.javaClass.getSimpleName())
                .commit()
    }

    private fun startFragmentTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }
}
