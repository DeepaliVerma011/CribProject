package com.example.cribproject

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)


        val i: Intent =intent

        val title: String? =i.getStringExtra("1")
        val des: String? =i.getStringExtra("2")
        val thumbnail: String? =i.getStringExtra("3")

        textView11.setText(title)
        textView12.setText(des)
        Picasso.get().load(thumbnail).into(imageView1)

    }


}