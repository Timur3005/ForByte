package edu.timurmakhmutov.forbyte.presentation.finish

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.timurmakhmutov.forbyte.R
import edu.timurmakhmutov.forbyte.databinding.FragmentFinishBinding

class FinishFragment : Fragment() {

    private lateinit var binding: FragmentFinishBinding
    private lateinit var finishViewModel: FinishViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFinishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        finishViewModel = ViewModelProvider(this)[FinishViewModel::class.java]
        getResult()
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner){}
        restart()
    }

    private fun getResult(){
        finishViewModel.getResult()
        binding.tvResult.text = "Your score is ${finishViewModel.result.value}"
    }

    private fun restart(){
        binding.btnRestart.setOnClickListener {
            finishViewModel.cleanData()
            findNavController().navigate(R.id.action_finishFragment_to_choseModeFragment)
        }
    }
}