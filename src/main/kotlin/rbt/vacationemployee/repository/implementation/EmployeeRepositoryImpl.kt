package rbt.vacationemployee.repository.implementation

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.toErrorIfNull
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import rbt.vacationemployee.domain.Employee
import rbt.vacationemployee.error.NotFoundError
import rbt.vacationemployee.error.RepositoryError
import rbt.vacationemployee.extensions.toResult
import rbt.vacationemployee.repository.EmployeeRepository
import rbt.vacationemployee.repository.jpa.JpaEmployeeRepository

@Component
class EmployeeRepositoryImpl(
    private val jpaEmployeeRepository: JpaEmployeeRepository,
) : EmployeeRepository {
    override fun findEmployeeById(id: String): Result<Employee, RepositoryError> =
        jpaEmployeeRepository
            .toResult {
                jpaEmployeeRepository.findByIdOrNull(id)
            }.toErrorIfNull {
                NotFoundError("Employee with id: $id not found", "Employee")
            }
}
