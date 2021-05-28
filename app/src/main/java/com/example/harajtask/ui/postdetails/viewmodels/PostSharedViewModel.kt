package com.example.harajtask.ui.postdetails.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harajtask.data.models.Post

class PostSharedViewModel: ViewModel() {
    private val post: MutableLiveData<Post> = MutableLiveData()

    fun select(post: Post) {
        this.post.value = post
    }

    fun getSelectedPost(): LiveData<Post> {
        return this.post
    }
}