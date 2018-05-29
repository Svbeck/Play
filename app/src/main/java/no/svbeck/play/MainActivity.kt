package no.svbeck.play

import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.beust.klaxon.JsonObject


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONObject
import org.xml.sax.Parser
import org.xml.sax.helpers.ParserAdapter
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.transform.Result

class MainActivity : AppCompatActivity() {

    val url = "https://api.themoviedb.org/3/movie/550?api_key=ee1b94905d841b2c9579fb3059012985"

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
    inner class GetJson() : AsyncTask<String, Int, String >() {

        override fun doInBackground(vararg params: String?): String {
            try {
                val url = URL(params[0])
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "GET"
                httpURLConnection.connect()
                val result = httpURLConnection.inputStream.bufferedReader().readText()
                return result

            } catch (exception: Exception) {
                exception.printStackTrace()
                return ""
            }

        }

        override fun onPostExecute(result: String?) {
            tv_movie_data.setText(result)
        }
    }
}
