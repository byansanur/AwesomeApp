package com.byansanur.awesomeapp.ui.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.byansanur.awesomeapp.R
import com.byansanur.awesomeapp.common.SetAppBar
import com.byansanur.awesomeapp.databinding.FragmentDetailsPhotosBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class FragmentDetailsPhotos : Fragment() {

    private var _binding : FragmentDetailsPhotosBinding? = null
    private val binding get() = _binding!!

    private val args: FragmentDetailsPhotosArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {

            toolbar.navigationIcon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_round_arrow_back_ios_24) }
            toolbar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            toolbar.title = args.photoDetail.photographer

            Glide.with(this@FragmentDetailsPhotos)
                .load(args.photoDetail.src.portrait)
                .centerCrop()
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imgDetails)

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}