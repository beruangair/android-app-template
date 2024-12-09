package com.ariflutfhansah.template.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ariflutfhansah.template.R
import com.ariflutfhansah.template.databinding.ActivityMainBinding
import com.ariflutfhansah.template.databinding.FragmentHomeBinding
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = CarouselLayoutManager()
        CarouselSnapHelper().attachToRecyclerView(binding.recyclerView)

        val imageList = mutableListOf<Int>()
        imageList.add(R.drawable.one)
        imageList.add(R.drawable.two)
        imageList.add(R.drawable.three)
        imageList.add(R.drawable.four)
        imageList.add(R.drawable.five)

        val adapter = CarouselAdapter(imageList)
        binding.recyclerView.adapter = adapter

        // ListView
        val listView = _binding!!.listView
        val data = listOf("Item 1", "Item 2", "Item 3", "Item 4")

        val arrayAdapter : ArrayAdapter<String> = ArrayAdapter<String>(
            requireActivity(),android.R.layout.simple_list_item_1, data
        )
        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = data[position]
            Toast.makeText(requireContext(), "Anda memilih: $selectedItem", Toast.LENGTH_SHORT).show()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}