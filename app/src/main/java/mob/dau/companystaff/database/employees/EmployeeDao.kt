package mob.dau.companystaff.database.employees

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employees ORDER BY position DESC")
    fun getEmployees(): Flow<List<Employee>>

    @Query("SELECT * FROM employees WHERE position = :position ORDER BY name DESC")
    fun getEmployeesByPosition(position: String): Flow<List<Employee>>
}