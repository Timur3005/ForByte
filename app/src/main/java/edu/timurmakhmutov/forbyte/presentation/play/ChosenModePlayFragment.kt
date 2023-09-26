package edu.timurmakhmutov.forbyte.presentation.play

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import edu.timurmakhmutov.forbyte.R
import edu.timurmakhmutov.forbyte.databinding.FragmentChosenModePlayBinding
import edu.timurmakhmutov.forbyte.presentation.adapter.WatchItemsAdapter
import edu.timurmakhmutov.forbyte.presentation.memorize.MemorizeTimeZonesFragment
import edu.timurmakhmutov.forbyte.presentation.onewatch.OneWatchFragment

class ChosenModePlayFragment : Fragment() {

    private lateinit var binding: FragmentChosenModePlayBinding
    private lateinit var viewModel: PlayViewModel
    private lateinit var mode: String
    private lateinit var watchItemsAdapter: WatchItemsAdapter
    private lateinit var timerVM: TimerViewModel
    private var isStart: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gettingArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChosenModePlayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[PlayViewModel::class.java]
        timerVM = ViewModelProvider(this)[TimerViewModel::class.java]
        recyclerInit()
        settingClickListener()
        recyclerUpdate()
        modeTime()
        clickToFinish()
        observeTimeValue()
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner){}
        isStart = true
    }

    private fun observeTimeValue(){
        timerVM.timerValue.observe(viewLifecycleOwner){
            binding.tvTimer.text = it.toString()
            if (it == 0L){
                navigateToFinish()
            }
        }
    }

    private fun modeTime(){
        if (mode == MODE_TIME && !isStart) {
            val timer =
                object : CountDownTimer(MemorizeTimeZonesFragment.PLAY_AND_MEMORIZING_TIME, 1000) {
                    override fun onTick(p0: Long) {
                        timerVM.timerValue.value = (p0 / 1000)
                    }

                    override fun onFinish() {
//                        if (isAdded) {
//                            navigateToFinish()
//                        }
                    }
                }
            timer.start()
        }
    }

    private fun gettingArgs() {
        arguments?.let {
            mode = it.getString(PLAY_MODE) ?: throw RuntimeException("Unknown mode")
        } ?: throw RuntimeException("Arguments are null")
    }

    private fun recyclerUpdate() {
        viewModel.allItems.observe(viewLifecycleOwner) {
            watchItemsAdapter.submitList(it)
        }
    }

    private fun recyclerInit(){
        watchItemsAdapter = WatchItemsAdapter()
        binding.rvMemorize.layoutManager = GridLayoutManager(context, 3)
        binding.rvMemorize.adapter = watchItemsAdapter
    }

    private fun settingClickListener(){
        watchItemsAdapter.watchClickListener = {
            if (mode == MODE_TIME) {
                navigateToOneWatchFragment(OneWatchFragment.newInstanceTimeMode(it.id))
            }
            else if (mode == MODE_CASUAL){
                navigateToOneWatchFragment(OneWatchFragment.newInstanceCasualMode(it.id))
            }
        }
    }

    private fun navigateToFinish(){
        findNavController().navigate(R.id.action_chosenModePlayFragment_to_finishFragment)
    }

    private fun navigateToOneWatchFragment(bundle: Bundle){
        findNavController().navigate(R.id.action_chosenModePlayFragment_to_oneWatchFragment, bundle)
    }

    private fun clickToFinish(){
        binding.btnNavigateToFinishFragment.setOnClickListener {
            navigateToFinish()
        }
    }

    companion object{

        private const val PLAY_MODE = "play_mode"
        private const val MODE_TIME = "mode_time"
        private const val MODE_CASUAL = "mode_casual"

        fun newInstanceTimeMode() = Bundle().apply {
            putString(PLAY_MODE, MODE_TIME)
        }

        fun newInstanceCasualMode() = Bundle().apply {
            putString(PLAY_MODE, MODE_CASUAL)
        }

    }
}