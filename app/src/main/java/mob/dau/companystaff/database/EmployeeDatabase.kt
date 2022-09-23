package mob.dau.companystaff.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mob.dau.companystaff.database.employees.Employee
import mob.dau.companystaff.database.employees.EmployeeDao

@Database(entities = arrayOf(Employee::class), version = 1)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao

    companion object {
        @Volatile
        var INSTANCE: EmployeeDatabase? = null

        fun getDatabase(context: Context): EmployeeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    EmployeeDatabase::class.java,
                    "employee_database")
                    .createFromAsset("database/employee_database.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}