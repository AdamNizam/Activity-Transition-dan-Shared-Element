package com.example.myrecyclerview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
interface RecyclerViewClickListener {


    // method yang akan dipanggil di MainActivity
    fun onItemClicked(view: View, book: Book)
//    fun startActivity(context: Activity, intentDetail: Intent, toBundle: Bundle?)

}
