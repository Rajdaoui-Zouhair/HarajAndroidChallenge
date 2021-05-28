package com.example.harajtask.ui.home.repository

import android.content.Context
import com.example.harajtask.data.models.Post
import com.example.harajtask.data.source.TestData

class PostRepository(private val context: Context) {
    fun getPosts(): List<Post> {
        return TestData.getPostFromJsonAsset(context)
    }
}