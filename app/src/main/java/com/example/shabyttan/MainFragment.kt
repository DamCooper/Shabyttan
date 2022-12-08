package com.example.shabyttan

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.shabyttan.models.ArtData
import com.example.shabyttan.models.Artwork
import com.example.shabyttan.models.ArtworkResponse
import com.example.shabyttan.services.CreatorApiInterface
import com.example.shabyttan.services.CreatorApiService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.coroutines.launch


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
    private lateinit var art: ArtData

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        getArtworkData { artwork: ArtData ->
            run {
                art = artwork
                Log.d("TAG", artwork.toString())
                txt_title.text = artwork.title
                txt_author.text = artwork.creators[0].description.toString().split("(")[0]
                txt_year.text = artwork.creation_date
                txt_description.text = Html.fromHtml(artwork.wall_description)
                val resizeImage = artwork.images.web.url
                Glide.with(this)
                    .load(resizeImage)
                    .into(image)
            }
        }

    }


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val inflatedView = inflater.inflate(R.layout.fragment_main, container, false);

        inflatedView.like_button.setOnClickListener {
            val db = context?.let { it1 ->
                Room.databaseBuilder(
                    it1,
                    FavoriteDatabase::class.java, "favorites_database"
                ).allowMainThreadQueries().build()
            }
            val favoriteDao = db?.favoriteDao()
            lifecycleScope.launch {
                favoriteDao?.addFavorite(Favorite(art.id, 0,
                    art.title, art.creators[0].description.toString().split("(")[0]))
            }
        }

        return inflatedView
    }

    private fun getArtworkData(callback: (ArtData) -> Unit) {
        val apiService = CreatorApiService.getInstance().create(CreatorApiInterface::class.java)
        apiService.getArtworksList(92937).enqueue(object : Callback<ArtworkResponse> {
            override fun onFailure(call: Call<ArtworkResponse>, t: Throwable) {
                throw t
            }

            override fun onResponse(
                call: Call<ArtworkResponse>,
                response: Response<ArtworkResponse>
            ) {
                return callback(response.body()!!.artData)
            }

        })
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