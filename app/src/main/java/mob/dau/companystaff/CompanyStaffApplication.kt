package mob.dau.companystaff

import android.app.Application
import mob.dau.companystaff.database.EmployeeDatabase

class CompanyStaffApplication : Application() {
    val database: EmployeeDatabase by lazy {
        EmployeeDatabase.getDatabase(this)
    }
}