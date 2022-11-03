package com.example.shabyttan

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shabyttan.models.Creator
import com.example.shabyttan.models.CreatorResponse
import com.example.shabyttan.services.CreatorApiInterface
import com.example.shabyttan.services.CreatorApiService
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment() : Fragment() {
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_search, container, false)
    }
    var name: String = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_creators_list.layoutManager = LinearLayoutManager(requireActivity())
        rv_creators_list.setHasFixedSize(true)
        val edit = view.findViewById<EditText>(R.id.input_search_creator)
        view.findViewById<Button>(R.id.btn_search_creator).setOnClickListener{

            name = edit.text.toString()
            getCreatorData { creators : List<Creator> ->
                rv_creators_list.adapter = CreatorAdapter(creators)
            }
            view.hideKeyboard()
        }
    }
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun getCreatorData(callback: (List<Creator>) -> Unit) {
        val apiService = CreatorApiService.getInstance().create(CreatorApiInterface::class.java)
        apiService.getCreatorList(name).enqueue(object : Callback<CreatorResponse> {
            override fun onFailure(call: Call<CreatorResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<CreatorResponse>,
                response: Response<CreatorResponse>
            ) {
                return callback(response.body()!!.creators)
            }

        })
    }


}