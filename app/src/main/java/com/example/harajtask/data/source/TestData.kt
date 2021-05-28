package com.example.harajtask.data.source

import android.content.Context
import com.example.harajtask.data.models.Post
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type
import java.nio.charset.Charset


class TestData {
    companion object {
        fun getPostFromJsonAsset(context: Context): List<Post> {
            val gson = Gson()
            val listPostType: Type =
                object : TypeToken<List<Post?>?>() {}.type
            val jsonString: String
            jsonString = try {
                val `is`: InputStream = context.assets.open("data.json")
                val size: Int = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                String(buffer, Charset.forName("UTF-8"))
            } catch (e: IOException) {
                e.printStackTrace()
                "[]"
            }
            return gson.fromJson(jsonString, listPostType)
        }
    }
}