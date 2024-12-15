package com.ariflutfhansah.template.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ariflutfhansah.template.R
import com.ariflutfhansah.template.databinding.ActivityMainBinding
import com.ariflutfhansah.template.databinding.FragmentHomeBinding
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ariflutfhansah.template.ui.home.adapter.SliderAdapter
import com.ariflutfhansah.template.ui.home.model.SliderItem

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // Slider
    private val slideInterval = 3000L
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: SliderAdapter
    private var currentPage = 0
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Slider
        viewPager = binding.viewPager // Gunakan properti kelas
        val tabLayout: TabLayout = binding.tabLayout
        val sliderItems = listOf(
            SliderItem(R.drawable.image1, "Title 1"),
            SliderItem(R.drawable.image2, "Title 2")
        )
        adapter = SliderAdapter(sliderItems) // Gunakan properti kelas
        viewPager.adapter = adapter

        // Menghubungkan TabLayout dengan ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Page ${position + 1}" // Menampilkan nomor halaman di tab
        }.attach()

        // Mulai auto slide
        startAutoSlide()

        // --- ListView --- //
        val listView = binding.listView
        val data = listOf("Item 1", "Item 2", "Item 3", "Item 4")

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            requireActivity(), android.R.layout.simple_list_item_1, data
        )
        listView.adapter = arrayAdapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = data[position]
            Toast.makeText(requireContext(), "Anda memilih: $selectedItem", Toast.LENGTH_SHORT).show()
        }
        // --- End of ListView --- //

        return root
    }

    private fun startAutoSlide() {
        runnable = Runnable {
            if (currentPage == adapter.itemCount) {
                currentPage = 0
            }
            viewPager.setCurrentItem(currentPage++, true)
            handler.postDelayed(runnable, 3000) // Ganti slide setiap 3 detik
        }
        handler.postDelayed(runnable, 3000) // Mulai auto slide setelah 3 detik
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(runnable)
        _binding = null
    }

}