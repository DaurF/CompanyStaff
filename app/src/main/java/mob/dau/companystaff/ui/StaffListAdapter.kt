package mob.dau.companystaff.ui

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mob.dau.companystaff.database.employees.Employee
import mob.dau.companystaff.databinding.ListItemEmployeeBinding
import java.text.NumberFormat

class StaffListAdapter(
    private val onItemClick: (Employee) -> Unit
) : ListAdapter<Employee, StaffListAdapter.EmployeeViewHolder>(DiffCallback) {
    inner class EmployeeViewHolder(private val binding: ListItemEmployeeBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(employee: Employee) {
                binding.apply {
                    position.text = employee.position
                    name.text = employee.name
                    salary.text = NumberFormat.getCurrencyInstance().format(employee.salary)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val viewHolder = EmployeeViewHolder(ListItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClick(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Employee>() {
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem == newItem
        }
    }
}