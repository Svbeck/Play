package no.svbeck.play

import com.beust.klaxon.JsonObject
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Array


const val VOTE_COUNT = "vote_count"
const val ID = "id"
const val VIDEO = "video"
const val VOTE_AVERAGE = "vote_average"
const val TITLE = "title"
const val POPULARITY = "popularity"
const val POSTER_PATH = "poster_path"
const val ORIGINAL_LANGUAGE = "original_language"
const val ORIGINAL_TITLE = "original_title"
const val GENRE_IDS = "genre_ids"
const val BACKDROP_PATH = "backdrop_path"
const val ADULT = "adult"
const val OVERVIEW = "overview"
const val RELEASE_DATE = "release_date"

data class MovieData(
        var vote_count: Int,
        var id: Int,
        var video: Boolean,
        var vote_average: Int,
        var title: String,
        var popularity: Int,
        var poster_path: String,
        var original_language: String,
        var original_title: String,
        var genre_ids: JSONArray,
        var backdrop_path: String,
        var adult: Boolean,
        var overview: String,
        var release_date: String
    ){
    constructor(jsonObject: JSONObject): this(jsonObject.getInt(VOTE_COUNT), jsonObject.getInt(ID),
            jsonObject.getBoolean(VIDEO), jsonObject.getInt(VOTE_AVERAGE), jsonObject.getString(TITLE),
            jsonObject.getInt(POPULARITY), jsonObject.getString(POSTER_PATH), jsonObject.getString(ORIGINAL_LANGUAGE),
            jsonObject.getString(ORIGINAL_TITLE),jsonObject.getJSONArray(GENRE_IDS), jsonObject.getString(BACKDROP_PATH),
            jsonObject.getBoolean(ADULT), jsonObject.getString(OVERVIEW),jsonObject.getString(RELEASE_DATE))
}
