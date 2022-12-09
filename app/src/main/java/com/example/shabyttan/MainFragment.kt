package com.example.shabyttan

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.shabyttan.models.ArtData
import com.example.shabyttan.models.Artwork
import com.example.shabyttan.models.ArtworkResponse
import com.example.shabyttan.models.ArtworkResponse1
import com.example.shabyttan.services.CreatorApiInterface
import com.example.shabyttan.services.CreatorApiService
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.*
import kotlin.random.Random


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
        val path = context?.getFilesDir()
        val folder = File(path, "file")
        folder.mkdirs()
        val file = File(folder, "ids.txt")
        Log.d("TAG", file.readText())
        getArtworkData { artwork: ArtData ->
            run {
                art = artwork
                Log.d("TAG", artwork.toString())
                txt_title?.text = artwork.title
                if (artwork.creators.isNotEmpty())
                    txt_author.text = artwork.creators[0].description.toString().split("(")[0]
                else txt_author.text = "Unknown"
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
                favoriteDao?.addFavorite(
                    Favorite(
                        art.id, 0,
                        art.title, ""
                    )
                )
            }
        }

        return inflatedView
    }

    fun getRandomNumber(): Int {
        val currentDate = Date()
        val random =
            Random("${currentDate.day + 467}${currentDate.month}${currentDate.year}".toInt())
        return random.nextInt(1001) + 1
    }


    private fun getArtworkData(callback: (ArtData) -> Unit) {
        val apiService = CreatorApiService.getInstance().create(CreatorApiInterface::class.java)
        // 92937 94979
        val path = context?.getFilesDir()
        val folder = File(path, "file")
        folder.mkdirs()
        val file = File(folder, "ids.txt")
        val id = file.readText()
            .split(" ")[getRandomNumber()].toInt()
        apiService.getArtworksList(id).enqueue(object : Callback<ArtworkResponse> {
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