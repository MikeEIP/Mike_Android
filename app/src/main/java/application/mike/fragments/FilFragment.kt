package application.mike.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import application.mike.R

class FilFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_fil, container, false)
        return rootView
    }

    companion object {
        fun newInstance(): FilFragment {
            var fragment = FilFragment()
            var args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
