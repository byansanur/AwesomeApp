package com.byansanur.awesomeapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.byansanur.awesomeapp.R
import com.byansanur.awesomeapp.databinding.FragmentDetailsPhotosBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class FragmentDetailsPhotos : Fragment(R.layout.fragment_details_photos) {

    private var _binding : FragmentDetailsPhotosBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDetailsPhotosBinding.bind(view)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}