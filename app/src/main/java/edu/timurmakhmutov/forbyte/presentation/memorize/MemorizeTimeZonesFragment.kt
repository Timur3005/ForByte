package edu.timurmakhmutov.forbyte.presentation.memorize

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import edu.timurmakhmutov.forbyte.R
import edu.timurmakhmutov.forbyte.databinding.FragmentMemorizeTimeZonesBinding
import edu.timurmakhmutov.forbyte.presentation.ChosenModePlayFragment
import edu.timurmakhmutov.forbyte.presentation.adapter.WatchItemsAdapter

class MemorizeTimeZonesFragment : Fragment() {

    private lateinit var binding: FragmentMemorizeTimeZonesBinding
    private lateinit var viewModel: MemorizeTimeZoneViewModel
    private lateinit var watchesAdapter: WatchItemsAdapter

    private lateinit var mode: String
    private lateinit var currentBundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gettingArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMemorizeTimeZonesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateClickToPlayFragment()
        viewModel = ViewModelProvider(this)[MemorizeTimeZoneViewModel::class.java]
        recyclerInit()
        recyclerUpdate()
        modeCompleting()
    }

    private fun gettingArgs() {
        arguments?.let {
            mode = it.getString(PLAY_MODE) ?: throw RuntimeException("Unknown mode")
        } ?: throw RuntimeException("Arguments are null")
    }

    private fun modeCompleting(){
        if (mode == MODE_TIME){
            currentBundle = ChosenModePlayFragment.newInstanceTimeMode()
            val timer = object : CountDownTimer(PLAY_AND_MEMORIZING_TIME, 1000){
                override fun onTick(p0: Long) {
                    binding.tvTimer.text = (p0/1000).toString()
                }

                override fun onFinish() {
                    navigateToPlayFragment()
                }
            }
            timer.start()
        }
        else{
            currentBundle = ChosenModePlayFragment.newInstanceCasualMode()
        }

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

    private fun navigateClickToPlayFragment(){
        binding.btnNavigateToPlayFragment.setOnClickListener {
            navigateToPlayFragment()
        }
    }

    private fun navigateToPlayFragment() {
        findNavController().navigate(R.id.action_memorizeTimeZonesFragment2_to_chosenModePlayFragment, currentBundle)
    }

    companion object{
        const val PLAY_AND_MEMORIZING_TIME: Long = 11000

        private const val PLAY_MODE = "play_mode"
        private const val MODE_TIME = "mode_time"
        private const val MODE_CASUAL = "mode_casual"

        fun newInstanceTimeMode(): Bundle{
            return Bundle().apply {
                    putString(PLAY_MODE, MODE_TIME)
            }

        }

        fun newInstanceCasualMode(): Bundle{
            return Bundle().apply {
                    putString(PLAY_MODE, MODE_CASUAL)
            }
        }
    }
}