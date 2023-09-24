package edu.timurmakhmutov.forbyte.presentation.memorize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import edu.timurmakhmutov.forbyte.R
import edu.timurmakhmutov.forbyte.databinding.FragmentMemorizeTimeZonesBinding
import edu.timurmakhmutov.forbyte.presentation.adapter.WatchItemsAdapter
import java.util.jar.Attributes

class MemorizeTimeZonesFragment : Fragment() {

    private lateinit var binding: FragmentMemorizeTimeZonesBinding
    private lateinit var viewModel: MemorizeTimeZoneViewModel
    private lateinit var watchesAdapter: WatchItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMemorizeTimeZonesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToPlayFragment()
        viewModel = ViewModelProvider(this)[MemorizeTimeZoneViewModel::class.java]
        recyclerInit()
        recyclerUpdate()
    }

    private fun recyclerUpdate() {
        viewModel.allItems.observe(viewLifecycleOwner) {
            watchesAdapter.submitList(it)
        }
    }

    private fun recyclerInit(){
        watchesAdapter = WatchItemsAdapter()
        binding.rvMemorize.layoutManager = GridLayoutManager(context, 3)
        binding.rvMemorize.adapter = watchesAdapter
    }

    private fun navigateToPlayFragment(){
        binding.btnNavigateToPlayFragment.setOnClickListener {
            findNavController().navigate(R.id.action_memorizeTimeZonesFragment2_to_chosenModePlayFragment)
        }
    }

    companion object{

        private const val PLAY_MODE = "play_mode"
        private const val MODE_TIME = "mode_time"
        private const val MODE_CASUAL = "mode_casual"

        fun newInstanceTimeMode(){
            MemorizeTimeZonesFragment().apply {
                arguments = Bundle().apply {
                    putString(PLAY_MODE, MODE_TIME)
                }
            }
        }

        fun newInstanceCasualMode(){
            MemorizeTimeZonesFragment().apply {
                arguments = Bundle().apply {
                    putString(PLAY_MODE, MODE_CASUAL)
                }
            }
        }
    }

}