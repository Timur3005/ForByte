package edu.timurmakhmutov.forbyte.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import edu.timurmakhmutov.forbyte.R
import edu.timurmakhmutov.forbyte.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navigateToChoseModeFragment()
    }

    private fun navigateToChoseModeFragment() {
        binding.buttonPlay.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_choseModeFragment)
        }
    }
}