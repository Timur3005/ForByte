package edu.timurmakhmutov.forbyte

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.timurmakhmutov.forbyte.databinding.FragmentOneWatchBinding
import edu.timurmakhmutov.forbyte.presentation.play.ChosenModePlayFragment

class OneWatchFragment : Fragment() {

    private lateinit var binding: FragmentOneWatchBinding
    private var watchId: Int = UNKNOWN_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gettingArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentOneWatchBinding.inflate(inflater, container, false)
        Log.d("MyTaggg", watchId.toString())
        return binding.root
    }

    private fun gettingArgs() {
        arguments?.let {
            watchId = it.getInt(ID)
        } ?: throw RuntimeException("Arguments are null")
    }

    companion object{

        private const val ID = "id"
        private const val UNKNOWN_ID = -1

        fun newInstanceDefault(id: Int) = Bundle().apply {
            putInt(ID, id)
        }
    }
}