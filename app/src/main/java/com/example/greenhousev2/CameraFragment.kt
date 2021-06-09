package com.example.greenhousev2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_camera.view.*

/**
 * A simple [Fragment] subclass.
 */
class CameraFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_camera, container, false)
        view.ivClose.setOnClickListener{
            val fragment: Fragment = ControlFragment()
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.main_container, fragment)
                ?.commitAllowingStateLoss()
        }

        return  view
    }

}
