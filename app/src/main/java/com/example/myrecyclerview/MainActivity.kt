package com.example.myrecyclerview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), RecyclerViewClickListener {

    private lateinit var rvBooks: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvBooks = findViewById(R.id.rv_books)
        rvBooks.setHasFixedSize(true)

        rvBooks.layoutManager = LinearLayoutManager(this)
        val listBookAdapter = ListBookAdapter(listBook)
        rvBooks.adapter = listBookAdapter

        val listHeroAdapter = ListBookAdapter(listBook)
        rvBooks.adapter = listHeroAdapter
        listHeroAdapter.clickListener = this

        findViewById<Button>(R.id.btn_about).setOnClickListener {

            val intentToAbout = Intent(this,AboutActivity::class.java)

            val option = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
            startActivity(intentToAbout,option.toBundle())
        }
    }

   private val listBook: ArrayList<Book>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataDescription = resources.getStringArray(R.array.data_description)
            val dataPhoto = resources.getStringArray(R.array.data_photo)
            val listBook = ArrayList<Book>()
            for (i in dataName.indices) {
                val book = Book(dataName[i], dataDescription[i], dataPhoto[i])
                listBook.add(book)
            }
            return listBook
        }

    override fun onItemClicked(view: View, book: Book) {
        // Dapatkan elemen UI dari view yang diklik
        val imgItemPhoto = view.findViewById<ImageView>(R.id.img_item_photo)
        val tvItemName = view.findViewById<TextView>(R.id.tv_item_name)
        val tvItemDescription = view.findViewById<TextView>(R.id.tv_item_description)

        // Buat Intent untuk berpindah ke DetailActivity
        val intentDetail = Intent(view.context, DetailActivity::class.java)
        intentDetail.putExtra("extra_detail", book)

        // Terapkan animasi transisi dengan shared elements
        val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
            view.context as Activity,
            Pair(imgItemPhoto, "profile"),
            Pair(tvItemName, "name"),
            Pair(tvItemDescription, "description")
        )

     startActivity(intentDetail,optionsCompat.toBundle())
    }

}