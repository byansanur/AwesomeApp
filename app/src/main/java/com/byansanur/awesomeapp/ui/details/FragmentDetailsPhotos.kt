package com.byansanur.awesomeapp.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.byansanur.awesomeapp.R
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

            toolbarDetails.navigationIcon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_round_arrow_back_ios_24) }
            toolbarDetails.title = args.photoDetail.photographer
            toolbarDetails.setNavigationOnClickListener {
                activity?.onBackPressed()
            }

            Glide.with(this@FragmentDetailsPhotos)
                .load(args.photoDetail.src.portrait)
                .fitCenter()
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imgDetails)

            tvPhotographer.text = args.photoDetail.photographer

            btnFollow.setOnClickListener { goToUrl(args.photoDetail.photographerUrl) }
            btnLikes.setOnClickListener { goToUrl(args.photoDetail.url) }
            btnShare.setOnClickListener {
                ShareCompat.IntentBuilder.from(requireActivity())
                    .setType("text/plain")
                    .setChooserTitle("Share is sharing")
                    .setText("I found this nice photo on pexels, have a look here ${args.photoDetail.url}")
                    .startChooser()
            }

        }
    }

    private fun goToUrl(url: String) {
        val uriUrl: Uri = Uri.parse(url)
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}