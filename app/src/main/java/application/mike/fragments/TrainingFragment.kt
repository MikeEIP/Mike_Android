package application.mike.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import application.mike.R

class TrainingFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_training, container, false)
        return rootView
    }
    companion object {
        fun newInstance(): TrainingFragment {
            var fragmentHome = TrainingFragment()
            var args = Bundle()
            fragmentHome.arguments = args
            return fragmentHome
        }
    }
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //var editTextHome = view!!.findViewById(R.id.textTraining) as TextView
    }
}