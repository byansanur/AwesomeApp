package com.byansanur.awesomeapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.byansanur.awesomeapp.R
import com.byansanur.awesomeapp.data.model.PhotoList
import com.byansanur.awesomeapp.databinding.FragmentHomeBinding
import com.byansanur.awesomeapp.ui.home.adapter.PhotoLoadStateAdapter
import com.byansanur.awesomeapp.ui.home.adapter.PhotosAdapterGrid
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class FragmentHome : Fragment(R.layout.fragment_home), PhotosAdapterGrid.OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel : PhotosViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        setViewGrid()

        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.grid_menu -> {
                setViewGrid()
            }
            R.id.linear_menu -> {
                setViewLinear()
            }
            else -> {
                Log.e("TAG", "onOptionsItemSelected: no menu selected")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setViewGrid() {
        val adapter = PhotosAdapterGrid(this)

        binding.apply {
            rvListPhoto.setHasFixedSize(true)
            rvListPhoto.adapter = adapter.withLoadStateHeaderAndFooter(
                header = PhotoLoadStateAdapter { adapter.retry() },
                footer = PhotoLoadStateAdapter { adapter.retry() }
            )
            rvListPhoto.layoutManager = GridLayoutManager(context, 2)
//            buttonRetry.setOnClickListener { adapter.retry() }
        }

        viewModel.photos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
            Log.e("TAG", "onViewCreated: $it")
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                pbLoading.isVisible = loadState.source.refresh is LoadState.Loading
                rvListPhoto.isVisible = loadState.source.refresh is LoadState.NotLoading
//                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
//                tvError.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    rvListPhoto.isVisible = false
//                    tvError.isVisible = true
                } else {
//                    tvError.isVisible = false
                }

            }
        }
    }

    private fun setViewLinear() {

    }


    override fun onItemClick(photo: PhotoList) {
        val action = photo.id
        Toast.makeText(context, "$action", Toast.LENGTH_LONG).show()
        Log.e("TAG", "onItemClick: $action")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}