package com.hs_worms.android.fragmentexample

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class ButtonsFragment : Fragment() {

    interface ActivityCallbacks {
        fun onNavigationSelected(elementNr: Int)
    }

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button

    private var activityCallbacks: ActivityCallbacks? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_1, container, false)

        button1 = view.findViewById(R.id.button1)
        button2 = view.findViewById(R.id.button2)
        button3 = view.findViewById(R.id.button3)

        button1.setOnClickListener(ElementButtonClickListener())
        button2.setOnClickListener(ElementButtonClickListener())
        button3.setOnClickListener(ElementButtonClickListener())

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityCallbacks = context as ActivityCallbacks?
    }

    override fun onDetach() {
        super.onDetach()
        activityCallbacks = null
    }

    private inner class ElementButtonClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            val elementNr = when(v?.id) {
                R.id.button1 -> 1
                R.id.button2 -> 2
                else -> 3
            }
            activityCallbacks?.onNavigationSelected(elementNr)
        }

    }

}
