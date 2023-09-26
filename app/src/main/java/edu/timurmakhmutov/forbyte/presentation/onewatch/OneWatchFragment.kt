package edu.timurmakhmutov.forbyte.presentation.onewatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.timurmakhmutov.forbyte.R
import edu.timurmakhmutov.forbyte.databinding.FragmentOneWatchBinding
import edu.timurmakhmutov.forbyte.presentation.play.TimerViewModel

class OneWatchFragment : Fragment() {

    private lateinit var binding: FragmentOneWatchBinding
    private var watchId: Int = UNKNOWN_ID
    private lateinit var timerVM: TimerViewModel
    private lateinit var oneWatchViewModel: OneWatchViewModel
    private lateinit var mode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gettingArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentOneWatchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timerVM = ViewModelProvider(this)[TimerViewModel::class.java]
        oneWatchViewModel = ViewModelProvider(this)[OneWatchViewModel::class.java]
//        workWithMode()
        gettingItem()
        settingDataInView()
        answerClickListener()
    }

    private fun gettingItem() {
        if (watchId != UNKNOWN_ID) {
            oneWatchViewModel.getWatchItemById(watchId)
        } else {
            throw RuntimeException("unknown id")
        }
    }

    private fun answerClickListener(){
        binding.btnAnswer.setOnClickListener {
            oneWatchViewModel.answerToQuestion(binding.etWatchItem.text?.toString())
            activity?.onBackPressed()
        }
    }

    private fun gettingArgs() {
        arguments?.let {
            watchId = it.getInt(ID)
            mode = it.getString(PLAY_MODE) ?: throw RuntimeException("mode is null")
        } ?: throw RuntimeException("Arguments are null")
    }

    private fun settingDataInView(){
        oneWatchViewModel.oneWatchItem.observe(viewLifecycleOwner){
            if (it != null) {
                binding.imageOneWatch.setImageResource(it.watchImageId)
            }
        }
    }

    private fun workWithMode(){
        if (mode == MODE_TIME){
            timerVM.timerValue.observe(viewLifecycleOwner){
                binding.tvTimer.text = it.toString()
                if (it == 0L){
                    findNavController().navigate(R.id.action_oneWatchFragment_to_finishFragment)
                }
            }
        }
    }

    companion object{

        private const val ID = "id"
        private const val UNKNOWN_ID = -1
        private const val PLAY_MODE = "play_mode"
        private const val MODE_TIME = "mode_time"
        private const val MODE_CASUAL = "mode_casual"

        fun newInstanceCasualMode(id: Int) = Bundle().apply {
            putInt(ID, id)
            putString(PLAY_MODE, MODE_CASUAL)
        }

        fun newInstanceTimeMode(id: Int) = Bundle().apply {
            putInt(ID, id)
            putString(PLAY_MODE, MODE_TIME)
        }
    }
}