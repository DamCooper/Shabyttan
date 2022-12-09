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
import com.example.shabyttan.models.*
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
    private lateinit var key: String

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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_creators_list.layoutManager = LinearLayoutManager(requireActivity())
        rv_creators_list.setHasFixedSize(true)
        val edit = view.findViewById<EditText>(R.id.input_search_creator)
        key = "Vincent Van Gogh"
        getArtworkDataByKey {
            rv_creators_list.adapter = ArtworkAdapter(it)
        }

        view.findViewById<Button>(R.id.btn_search_creator).setOnClickListener{
            key = edit.text.toString()
            getArtworkDataByKey {
                rv_creators_list.adapter = ArtworkAdapter(it)
            }
            view.hideKeyboard()
        }
    }
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun getArtworkDataByKey(callback: (List<ArtData>) -> Unit) {
        val apiService = CreatorApiService.getInstance().create(CreatorApiInterface::class.java)
        // 92937 94979
        apiService.getArtworksByKey(key).enqueue(object : Callback<ArtworkResponse1> {
            override fun onFailure(call: Call<ArtworkResponse1>, t: Throwable) {
                throw t
            }

            override fun onResponse(
                call: Call<ArtworkResponse1>,
                response: Response<ArtworkResponse1>
            ) {
                return callback(response.body()!!.artData)
            }

        })
    }

    private fun getCreatorData(callback: (List<Creator>) -> Unit) {
        val apiService = CreatorApiService.getInstance().create(CreatorApiInterface::class.java)
        apiService.getCreatorList(key).enqueue(object : Callback<CreatorResponse> {
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