package com.example.shabyttan

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val inflatedView = inflater.inflate(R.layout.fragment_main, container, false);
        val scrollView: NestedScrollView = inflatedView.findViewById(R.id.scrollview)

        scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY) {
               // Log.i(TAG, "Scroll DOWN")
                val b = inflatedView.findViewById<FloatingActionButton>(R.id.refresh_button)
                println("sadasdasda")
                b.backgroundTintList = ColorStateList.valueOf(1234);
                Toast.makeText(requireContext(),"this is toast message",Toast.LENGTH_SHORT).show()
            }
            if (scrollY < oldScrollY) {
//                Log.i(TAG, "Scroll UP")
                val b = inflatedView.findViewById<FloatingActionButton>(R.id.refresh_button)
                println("sadasdasda")
                b.backgroundTintList = ColorStateList.valueOf(1234);
                Toast.makeText(requireContext(),"this is toast message",Toast.LENGTH_SHORT).show()
            }
            if (scrollY == 0) {
//                Log.i(TAG, "TOP SCROLL")
                Toast.makeText(requireContext(),"this is toast message",Toast.LENGTH_SHORT).show()

            }
            if (scrollY == v.measuredHeight - v.getChildAt(0).measuredHeight) {
//                Log.i(TAG, "BOTTOM SCROLL")
                Toast.makeText(requireContext(),"this is toast message",Toast.LENGTH_SHORT).show()

            }
        })


        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}