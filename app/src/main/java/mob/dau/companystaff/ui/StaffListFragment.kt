package mob.dau.companystaff.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mob.dau.companystaff.CompanyStaffApplication
import mob.dau.companystaff.databinding.FragmentStaffListBinding
import mob.dau.companystaff.viewmodels.CompanyStaffViewModel
import mob.dau.companystaff.viewmodels.CompanyStaffViewModelFactory

class StaffListFragment : Fragment() {
    private val viewModel: CompanyStaffViewModel by activityViewModels {
        CompanyStaffViewModelFactory(
            (activity?.application as CompanyStaffApplication).database.employeeDao()
        )
    }

    private var _binding: FragmentStaffListBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStaffListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = StaffListAdapter {
            val action = StaffListFragmentDirections.actionStaffListFragmentToStaffListByPositionFragment(it.position)
            binding.root.findNavController().navigate(action)
        }
        recyclerView.adapter = adapter

        lifecycle.coroutineScope.launch {
            viewModel.getStaff().collect {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}