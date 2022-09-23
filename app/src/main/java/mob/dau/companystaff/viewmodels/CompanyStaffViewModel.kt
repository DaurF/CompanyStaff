package mob.dau.companystaff.viewmodels

import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mob.dau.companystaff.database.employees.Employee
import mob.dau.companystaff.database.employees.EmployeeDao
import java.lang.IllegalArgumentException

class CompanyStaffViewModel(private val employeeDao: EmployeeDao) : ViewModel() {

    fun getStaff(): Flow<List<Employee>> = employeeDao.getEmployees()

    fun getStaffByPosition(position: String): Flow<List<Employee>> = employeeDao.getEmployeesByPosition(position)
}

class CompanyStaffViewModelFactory(
    private val employeeDao: EmployeeDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CompanyStaffViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CompanyStaffViewModel(employeeDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}