package edu.timurmakhmutov.forbyte.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.timurmakhmutov.forbyte.R
import edu.timurmakhmutov.forbyte.databinding.FragmentChoseModeBinding
import edu.timurmakhmutov.forbyte.databinding.FragmentChosenModePlayBinding

class ChosenModePlayFragment : Fragment() {

    private lateinit var binding: FragmentChosenModePlayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChosenModePlayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object{

        private const val PLAY_MODE = "play_mode"
        private const val MODE_TIME = "mode_time"
        private const val MODE_CASUAL = "mode_casual"

        fun newInstanceTimeMode(){
            ChosenModePlayFragment().apply {
                arguments = Bundle().apply {
                    putString(PLAY_MODE, MODE_TIME)
                }
            }
        }

        fun newInstanceCasualMode(){
            ChosenModePlayFragment().apply {
                arguments = Bundle().apply {
                    putString(PLAY_MODE, MODE_CASUAL)
                }
            }
        }
    }
}