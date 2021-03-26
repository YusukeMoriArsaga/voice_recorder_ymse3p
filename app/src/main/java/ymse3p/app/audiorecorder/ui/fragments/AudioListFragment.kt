package ymse3p.app.audiorecorder.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ymse3p.app.audiorecorder.R
import ymse3p.app.audiorecorder.adapter.AudioAdapter
import ymse3p.app.audiorecorder.databinding.FragmentFirstBinding
import ymse3p.app.audiorecorder.viewmodels.MainViewModel
import ymse3p.app.audiorecorder.viewmodels.PlayBackViewModel



@AndroidEntryPoint
class AudioListFragment : Fragment() {

    private val mainViewModel by activityViewModels<MainViewModel>()
    private val playBackViewModel by activityViewModels<PlayBackViewModel>()

    private lateinit var _binding: FragmentFirstBinding
    private val binding get() = _binding

    private val mAdapter by lazy { AudioAdapter(mainViewModel,playBackViewModel,requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(layoutInflater, container, false)
        setupRecyclerView()
        readDatabase()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        mAdapter.clearContextualActionMode()
    }

    private fun setupRecyclerView() {
        binding.audioListRecyclerview.adapter = mAdapter
        binding.audioListRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun readDatabase() {
        mainViewModel.readAudio.observe(viewLifecycleOwner, { database ->
            mAdapter.setData(database)
            hideShimmerEffect()

        })

    }

    private fun hideShimmerEffect() {
        binding.audioListRecyclerview.hideShimmer()
    }

    private fun showShimmerEffect() {
        binding.audioListRecyclerview.showShimmer()
    }
}