package rbt.vacationemployee.repository

import com.github.michaelbull.result.Result
import rbt.vacationemployee.domain.Employee
import rbt.vacationemployee.error.RepositoryError

interface EmployeeRepository {
    fun findEmployeeById(id: String): Result<Employee, RepositoryError>
}
