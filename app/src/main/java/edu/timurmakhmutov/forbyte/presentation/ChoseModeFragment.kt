package edu.timurmakhmutov.forbyte.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import edu.timurmakhmutov.forbyte.R
import edu.timurmakhmutov.forbyte.databinding.FragmentChoseModeBinding
import edu.timurmakhmutov.forbyte.presentation.memorize.MemorizeTimeZonesFragment

class ChoseModeFragment : Fragment() {

    private lateinit var binding: FragmentChoseModeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChoseModeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        casualModeSettings()
        timeModeSettings()
    }

    private fun casualModeSettings(){
        binding.btnCasual.setOnClickListener {
            ChosenModePlayFragment.newInstanceCasualMode()
            MemorizeTimeZonesFragment.newInstanceCasualMode()
            navigateToChosenModePlayFragment()
        }
    }

    private fun timeModeSettings(){
        binding.btnTime.setOnClickListener {
            ChosenModePlayFragment.newInstanceTimeMode()
            MemorizeTimeZonesFragment.newInstanceTimeMode()
            navigateToChosenModePlayFragment()
        }
    }

    private fun navigateToChosenModePlayFragment(){
        findNavController().navigate(R.id.action_choseModeFragment_to_memorizeTimeZonesFragment2)
    }


}