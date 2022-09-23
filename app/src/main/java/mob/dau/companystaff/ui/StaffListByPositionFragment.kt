package mob.dau.companystaff.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mob.dau.companystaff.CompanyStaffApplication
import mob.dau.companystaff.R
import mob.dau.companystaff.databinding.FragmentStaffListByPositionBinding
import mob.dau.companystaff.viewmodels.CompanyStaffViewModel
import mob.dau.companystaff.viewmodels.CompanyStaffViewModelFactory

class StaffListByPositionFragment : Fragment() {
    private val viewModel: CompanyStaffViewModel by viewModels {
        CompanyStaffViewModelFactory(
            (activity?.application as CompanyStaffApplication).database.employeeDao()
        )
    }

    companion object {
        const val POSITION = "position"
    }

    private var _binding: FragmentStaffListByPositionBinding? = null
    private val binding: FragmentStaffListByPositionBinding
        get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private lateinit var position: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            this@StaffListByPositionFragment.position = it.getString(POSITION).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStaffListByPositionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = StaffListAdapter({})
        recyclerView.adapter = adapter

        lifecycle.coroutineScope.launch {
            viewModel.getStaffByPosition(position).collect {
                adapter.submitList(it)
            }
        }
    }
}