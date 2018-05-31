package no.svbeck.play

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

import no.svbeck.play.R.id.fab

import no.svbeck.play.R.id.toolbar
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONStringer
import org.xml.sax.Parser
import org.xml.sax.helpers.ParserAdapter
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.transform.Result

val API_KEY: String = "ee1b94905d841b2c9579fb3059012985"
val url: String = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        GetJson().execute(url)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    //Class for asynchronous
    inner class GetJson() : AsyncTask<String, Int, String >() {

        override fun onPreExecute() {
            //TODO 1: Create progressbar
            pb_circular_progressbar.show()
        }
        override fun doInBackground(vararg params: String?): String {
            return try{

            assets.open("discover.txt")
                    .bufferedReader()
                    .readText()
           /* return try {
                val url = URL(params[0])    //url string from params
                val httpURLConnection = url.openConnection() as HttpURLConnection //type of http connection
                httpURLConnection.requestMethod = "GET" //get method
                httpURLConnection.connect() //connect to webpage
                httpURLConnection.inputStream.bufferedReader().readText() //read json as string and returns
            */
            } catch (e: Exception) { //catch possible exceptions
                e.printStackTrace()
                throw e
            }catch (e:IOException){
                e.printStackTrace()
                throw e}
        }

        override fun onPostExecute(result: String?) {
            //TODO 2: Get json data from tmdb and put it in a class
            //TODO 3: Create recyclerView https://developer.android.com/guide/topics/ui/layout/recyclerview#kotlin
            //TODO 4: Bind Movie data to fragment
            //TODO 5: Get poster image from tmdb and polish gui
            //TODO 6: Create fragments to display cards side by side on larger screens
            //TODO 6.1: Create Fragment https://www.raywenderlich.com/169885/android-fragments-tutorial-introduction-2
            //TODO 6.2: Add Fragment
            //TODO 6.3: DataBinding
            //TODO 6.4: Communicate with activity (show detiails)
            //TODO 6.5: use viewpager to slide between mvieDetails https://developer.android.com/training/animation/screen-slide

            lateinit var movieInfo: JSONObject //json object containing info about movie
            try {
                val movieList = mutableListOf<MovieData>()
                val jsonObject = JSONObject(result) //get movie results from most popular movies
                val jsonArray = jsonObject.getJSONArray("results") //put objects into array

                pb_circular_progressbar.hide() //hide progress bar
                for (i in 0..(jsonArray.length() - 1)) { //iterates through length of result
                    movieInfo = jsonArray.getJSONObject(i) //turn each movie to an movie object
                    movieList.add(MovieData(movieInfo))   //Add Json object to Movie
                }
                tv_movie_data.setText(movieInfo.toString()) // json data as text

            } catch (e: JSONException) { //Catch errors for json parsing
                tv_movie_data.setText(e.toString())
            }
        }

        fun someMethodReadsAssetFile(contextInstance: Context) {
            contextInstance.assets
        }
    }
}
