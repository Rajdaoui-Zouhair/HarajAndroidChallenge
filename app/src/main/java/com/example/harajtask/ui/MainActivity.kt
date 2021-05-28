package com.example.harajtask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.harajtask.data.models.Post
import com.example.harajtask.databinding.ActivityMainBinding
import com.example.harajtask.generics.ItemClick
import com.example.harajtask.ui.home.adapters.PostListAdapter
import com.example.harajtask.ui.home.viewmodel.PostViewModel
import com.example.harajtask.ui.postdetails.PostDetailsDialogFragment
import com.example.harajtask.ui.postdetails.viewmodels.PostSharedViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var postViewModel: PostViewModel
    private lateinit var postListAdapter: PostListAdapter
    private lateinit var postSharedViewModel: PostSharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        postSharedViewModel = ViewModelProvider(this).get(PostSharedViewModel::class.java)

        postListAdapter = PostListAdapter(postListItemClickListener, postViewModel.getPost())
        binding.postListRv.adapter = postListAdapter
    }

    private val postListItemClickListener = object : ItemClick<Post> {
        override fun onItemClick(t: Post) {
            postSharedViewModel.select(post = t)

            PostDetailsDialogFragment
                .newInstance()
                .show(supportFragmentManager, PostDetailsDialogFragment.newInstance().tag)
        }
    }
}