package com.example.harajtask.ui.postdetails

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.harajtask.R
import com.example.harajtask.databinding.FragmentPostDetailsBinding
import com.example.harajtask.ui.postdetails.viewmodels.PostSharedViewModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [DialogFragment] subclass.
 * Use the [PostDetailsDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostDetailsDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentPostDetailsBinding
    private var postTitleToShare: String = ""

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment PostDetailsFragment.
         */
        @JvmStatic
        fun newInstance() =
            PostDetailsDialogFragment().apply {}
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        val dialog = Dialog(requireContext())
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.window?.statusBarColor = Color.WHITE
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog.setContentView(
            RelativeLayout(requireContext()),
            ViewGroup.LayoutParams(width, height)
        )
        dialog.window?.setLayout(width, height)
        dialog.window?.attributes?.windowAnimations = R.style.dialog_fragment_animation
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPostDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postSharedViewModel: PostSharedViewModel = ViewModelProvider(requireActivity()).get(PostSharedViewModel::class.java)

        postSharedViewModel.getSelectedPost().observe(viewLifecycleOwner, Observer {
            Glide.with(requireContext()).load(it.thumbURL).into(binding.ivPostThumb)
            binding.tvPostTitle.text = it.title
            postTitleToShare = it.title

            binding.tvPostDate.text = convertLongToTime(it.date)
            binding.tvPostLocation.text = it.city
            binding.tvPostUser.text = it.userName
            binding.tvPostBody.text = it.body
            binding.tvPostBody.movementMethod = ScrollingMovementMethod()
        })

        binding.ivShareIcon.setOnClickListener(shareButtonClickListener)
    }

    val shareButtonClickListener = View.OnClickListener {
        if (postTitleToShare.isNotEmpty()) {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, postTitleToShare)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, postTitleToShare)
            startActivity(shareIntent)
        }
    }

    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm")
        return format.format(date)
    }
}