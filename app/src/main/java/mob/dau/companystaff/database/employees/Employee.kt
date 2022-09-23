package mob.dau.companystaff.database.employees

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "employees")
data class Employee(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @NotNull val position: String,
    @NotNull val name: String,
    @NotNull val salary: Double
)