package com.example.cribproject
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.util.Log.DEBUG
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.BuildConfig.DEBUG
import com.example.cribpro.CutsomAdapter
import com.example.cribproject.BuildConfig.DEBUG
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //We can also use Retrofit,I am using Okhttp.
         val client=OkHttpClient()
         var arrayList_details:ArrayList<User>
        val request = Request.Builder()
            .url("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&gpssearch=Sachin+T&gpslimit=10")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle this
            }

            override fun onResponse(call: Call, response: Response) {
                // Handle this
                var str_response = response.body!!.string()
                //creating json object
                val json_contact: JSONObject = JSONObject(str_response)
                //creating json array

                val json_mid:JSONObject=json_contact.getJSONObject("query")
                var jsonarray_info: JSONArray = json_mid.getJSONArray("pages")




                var i:Int = 0
                var size:Int = jsonarray_info.length()
                arrayList_details= ArrayList();
                for (i in 0.. size-1) {

                    try {
                        var json_objectdetail: JSONObject = jsonarray_info.getJSONObject(i)
                        var model: User = User();


                        model.title = json_objectdetail.getString("title")

                        System.out.println(model.title);
                        Log.d("DEBUG", model.title + "hello");
                        var thumb: JSONObject = json_objectdetail.getJSONObject("thumbnail")

                        if(thumb==null){
                            model.thumbnail="https://in.images.search.yahoo.com/search/images?p=contact+person+logo&fr=mcafee&type=E211IN826G0&imgurl=https%3A%2F%2Fwww.clipartkey.com%2Fmpngs%2Fm%2F96-966685_contact-person-icon-png.png#id=2&iurl=https%3A%2F%2Fwww.clipartkey.com%2Fmpngs%2Fm%2F96-966685_contact-person-icon-png.png&action=click"
                        }
                        else {
                            var source: String = thumb.getString("source")
                            Log.d("DEBUG", source + "source");
                            System.out.println(source);
                            model.thumbnail = source.toString()





                    }

                        var term:JSONObject=json_objectdetail.getJSONObject("terms")
                        var des:JSONArray=term.getJSONArray("description")

                        model.des= des[0].toString();
                        Log.e("DEBUG",model.thumbnail+"my_thumbnail");
                        arrayList_details.add(model)

                        Log.d("DEBUG", arrayList_details.get(arrayList_details.size - 1).toString())

                    }
                    catch (e: JSONException){
                        e.printStackTrace()
                    }
                }

                var sizee:Int=arrayList_details.size
                Log.d("DEBUG", sizee.toString())
                var j : Int;
                for(i in 0 until sizee){
                    Log.d("DEBUG", arrayList_details[i].title + "-------"+ arrayList_details[i].thumbnail)
                }
                runOnUiThread {
                    //stuff that updates ui
                    var obj_adapter : CutsomAdapter
                    Log.d("DEBUG","message")


                   val recyclerView:RecyclerView=findViewById<RecyclerView>(R.id.userRv)
                    obj_adapter = CutsomAdapter(this@MainActivity,arrayList_details)

                    val layoutManager=LinearLayoutManager(applicationContext)
                    Log.d("DEBUG","I AM AT RECYCLER VIEW")
                    recyclerView.layoutManager=layoutManager
                    Log.d("DEBUG","I AM AT LINEARLAYOUT MANAGER")
                    recyclerView.adapter=obj_adapter

                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            // on below line we are checking
                            // if query exist or not.

                            val i:Int=0
                            val list:ArrayList<User>
                            list=ArrayList()

                            Log.d("DEBUG","making List")
                            for(i in 0 until sizee-1){
                                if(query?.let { arrayList_details.get(i).title.contains(it) } == true) {
                                    list.add(arrayList_details.get(i))
                                }
                            }


                            if(list.size==0){
                                Log.d("DEBUG","zero List")
                                Toast.makeText(this@MainActivity,"No user found",Toast.LENGTH_LONG)
                            }
                            else{
                                obj_adapter = CutsomAdapter(this@MainActivity,list)

                                recyclerView.adapter=obj_adapter
                            }


                            return true
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {

                            //do nothing
                            if(newText=="" || newText.isNullOrBlank()){
                                obj_adapter = CutsomAdapter(this@MainActivity,arrayList_details)

                                val layoutManager=LinearLayoutManager(applicationContext)

                                recyclerView.adapter=obj_adapter
                            }
                            return true
                        }


                    })
                }

            }
        })


    }






}