package com.example.greenhousev2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_start.view.*


class StartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_start, container, false)


        view.btnStart.setOnClickListener{
            val fragment: Fragment = ControlFragment()
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.main_container, fragment)
                ?.commitAllowingStateLoss()
        }

        return view
    }


}
