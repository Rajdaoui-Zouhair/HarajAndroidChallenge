package com.example.harajtask.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.harajtask.application.HarajTast
import com.example.harajtask.data.models.Post
import com.example.harajtask.ui.home.repository.PostRepository

class PostViewModel : ViewModel() {
    private val postRepository = HarajTast.context?.let { PostRepository(it) }
    fun getPost(): List<Post>{
        if (postRepository != null) {
            return postRepository.getPosts()
        }
        return ArrayList()
    }
}