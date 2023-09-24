package edu.timurmakhmutov.forbyte.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.timurmakhmutov.forbyte.R
import edu.timurmakhmutov.forbyte.databinding.FragmentMemorizeTimeZonesBinding

class MemorizeTimeZonesFragment : Fragment() {

    private lateinit var binding: FragmentMemorizeTimeZonesBinding

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