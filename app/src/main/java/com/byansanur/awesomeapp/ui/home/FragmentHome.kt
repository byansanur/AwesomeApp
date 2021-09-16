package com.byansanur.awesomeapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.byansanur.awesomeapp.ApplicationAwesome
import com.byansanur.awesomeapp.R
import com.byansanur.awesomeapp.common.InternetConnection
import com.byansanur.awesomeapp.common.SetAppBar
import com.byansanur.awesomeapp.data.model.PhotoList
import com.byansanur.awesomeapp.databinding.FragmentHomeBinding
import com.byansanur.awesomeapp.ui.home.adapter.OnItemClickListener
import com.byansanur.awesomeapp.ui.home.adapter.PhotoLoadStateAdapter
import com.byansanur.awesomeapp.ui.home.adapter.PhotosAdapterGrid
import com.byansanur.awesomeapp.ui.home.adapter.PhotosAdapterLinear
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class FragmentHome : Fragment(), OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel : PhotosViewModel by viewModels()

    private var itemGrid: MenuItem? = null
    private var itemLinear: MenuItem? = null
    private var listMenuItem: MutableList<MenuItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewGrid()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val setAppBar = context?.let { SetAppBar(it) }
        setAppBar?.setCollapseAppBar(binding.appbar, binding.toolbar, listMenuItem)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        itemGrid = menu.findItem(R.id.grid_menu)
        itemLinear = menu.findItem(R.id.linear_menu)

        itemGrid?.let { listMenuItem.add(it) }
        itemLinear?.let { listMenuItem.add(it) }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.grid_menu -> setViewGrid()
            R.id.linear_menu -> setViewLinear()
            else -> Log.e("TAG", "onOptionsItemSelected: no menu selected")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setViewGrid() {
        val adapterGrid = PhotosAdapterGrid(this)
        val internetConnection = InternetConnection(requireContext())
        if (!internetConnection.hasNetwork()) {
            binding.apply {
                flError.isVisible = true
                incError.tvErrorMessage.text = getString(R.string.no_internet_message)
                incError.buttonRetry.setOnClickListener {
                    getDataGrid(adapterGrid)
                    adapterGrid.retry()
                }
            }
        } else getDataGrid(adapterGrid)

    }

    private fun getDataGrid(adapterGrid: PhotosAdapterGrid) {
        binding.apply {
            rvListPhoto.isNestedScrollingEnabled = true
            rvListPhoto.adapter = adapterGrid.withLoadStateFooter(
                footer = PhotoLoadStateAdapter { adapterGrid.retry() }
            )
            rvListPhoto.layoutManager = StaggeredGridLayoutManager(2, 1)
        }
        viewModel.photos.observe(viewLifecycleOwner) {
            adapterGrid.submitData(viewLifecycleOwner.lifecycle, it)
        }
        adapterGrid.addLoadStateListener { loadState ->
            binding.apply {
                when(loadState.source.refresh) {
                    is LoadState.Loading -> {
                        flError.isVisible = false
                        rvListPhoto.isVisible = true
                    }
                    is LoadState.NotLoading -> {
                        flError.isVisible = false
                        rvListPhoto.isVisible = true
                    }
                    is LoadState.Error -> {
                        rvListPhoto.isVisible = false
                        flError.isVisible = true
                        val errorMessage = (loadState.source.refresh as LoadState.Error).error
                        Log.e("TAG", "setViewGrid: message error: $errorMessage")
                    }
                }
            }
        }
    }

    private fun setViewLinear() {
        val adapterLinear = PhotosAdapterLinear(this)
        val internetConnection = InternetConnection(requireContext())
        if (internetConnection.hasNetwork()) {
            getDataLinear(adapterLinear)
        } else {
            binding.apply {
                flError.isVisible = true
                incError.tvErrorMessage.text = getString(R.string.no_internet_message)
                incError.buttonRetry.setOnClickListener {
                    getDataLinear(adapterLinear)
                    adapterLinear.retry()
                }
            }
        }
    }

    private fun getDataLinear(adapterLinear: PhotosAdapterLinear) {
        binding.apply {
            rvListPhoto.isNestedScrollingEnabled = true
            rvListPhoto.adapter = adapterLinear.withLoadStateHeaderAndFooter(
                header = PhotoLoadStateAdapter { adapterLinear.retry() },
                footer = PhotoLoadStateAdapter { adapterLinear.retry() }
            )
            rvListPhoto.layoutManager = LinearLayoutManager(context)
        }
        viewModel.photos.observe(viewLifecycleOwner) {
            adapterLinear.submitData(viewLifecycleOwner.lifecycle, it)
        }
        adapterLinear.addLoadStateListener { loadState ->
            binding.apply {
                when(loadState.source.refresh) {
                    is LoadState.Loading -> {
                        flError.isVisible = false
                        rvListPhoto.isVisible = false
                    }
                    is LoadState.NotLoading -> {
                        flError.isVisible = false
                        rvListPhoto.isVisible = true
                    }
                    is LoadState.Error -> {
                        flError.isVisible = true
                        rvListPhoto.isVisible = false
                        val errorMessage = (loadState.source.refresh as LoadState.Error).error
                        Log.e("TAG", "setViewGrid: message error: $errorMessage")
                    }
                }
            }
        }
    }


    override fun onItemClick(photo: PhotoList) {
        val nav = FragmentHomeDirections.actionFragmentHomeToFragmentDetailsPhotos(photo)
        findNavController().navigate(nav)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}