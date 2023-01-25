package com.example.cribpro

import com.example.cribproject.R
import com.example.cribproject.User
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row.view.*

class CutsomAdapter(context: Context,val arrayList: ArrayList<User>): RecyclerView.Adapter<CutsomAdapter.CustomViewHolder> (){
    class CustomViewHolder (itemView: View?):RecyclerView.ViewHolder(itemView!!) {

        fun bind(user: User) {
            itemView?.textView?.text = user.title
            if(user?.thumbnail!=null) {
                Picasso.get().load(user?.thumbnail).into(itemView.imageView)
            }
            else{
                Picasso.get().load("https://in.images.search.yahoo.com/search/images?p=contact+person+logo&fr=mcafee&type=E211IN826G0&imgurl=https%3A%2F%2Fwww.clipartkey.com%2Fmpngs%2Fm%2F96-966685_contact-person-icon-png.png#id=2&iurl=https%3A%2F%2Fwww.clipartkey.com%2Fmpngs%2Fm%2F96-966685_contact-person-icon-png.png&action=click").into(itemView.imageView)
            }

            itemView?.textView1?.text=user.des
        }
    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder =
            CustomViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_row,parent,false)
            )

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder?.bind(arrayList[position])
        }

        override fun getItemCount(): Int {
            return arrayList.size
        }
    }

