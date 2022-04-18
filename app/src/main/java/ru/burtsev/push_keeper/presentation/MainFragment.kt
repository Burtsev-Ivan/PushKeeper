package ru.burtsev.push_keeper.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.burtsev.push_keeper.databinding.MainFragmentBinding
import ru.burtsev.push_keeper.presentation.adapter.NotificationAdapter


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding get() = _binding!!

    private val viewModel: MainViewModel by viewModel()

    val adapter = NotificationAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNotification.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotification.adapter = adapter
        viewModel.notificationLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
