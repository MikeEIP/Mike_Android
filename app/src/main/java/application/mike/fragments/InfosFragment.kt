package application.mike.fragments

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.os.Bundle
import android.support.annotation.FloatRange
import android.support.v4.app.Fragment
import android.util.FloatMath
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import application.mike.R
import kotlinx.android.synthetic.main.fragment_infos.*
import kotlin.math.sqrt

class InfosFragment : Fragment(), SensorEventListener {

    lateinit var sensorManager: SensorManager

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    var nb : Int = 0

    override fun onSensorChanged(event: SensorEvent?) {

        var mAccelCurrent = SensorManager.GRAVITY_EARTH;
        var mAccelLast = SensorManager.GRAVITY_EARTH;
        var mAccel : Float = 0.0f

        var mGravity = event!!.values.clone()

        var x: Float = mGravity[0]
        var y: Float = mGravity[1]
        var z: Float = mGravity[2]

        mAccelLast = mAccelCurrent

        mAccelCurrent = sqrt(x*x + y*y + z*z)

        var delta = mAccelCurrent - mAccelLast

        mAccel = mAccel * 0.9f + delta

        println("${mAccel}")

        if (mAccel > 3) {
            nb++
            pompe.progress = nb * 10
            push_up.text = "${nb} / 10"
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager = activity.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL)



        }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_infos, container, false)
    }

    companion object {
        fun newInstance(param1: String, param2: String): InfosFragment {
            val fragment = InfosFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
