package rbt.vacationemployee.repository.implementation

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.toErrorIfNull
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import rbt.vacationemployee.domain.Vacation
import rbt.vacationemployee.domain.VacationId
import rbt.vacationemployee.error.NotFoundError
import rbt.vacationemployee.error.RepositoryError
import rbt.vacationemployee.extensions.toResult
import rbt.vacationemployee.repository.VacationRepository
import rbt.vacationemployee.repository.jpa.JpaVacationRepository

@Component
class VacationRepositoryImpl(
    private val jpaVacationRepository: JpaVacationRepository,
) : VacationRepository {
    override fun findVacationById(id: VacationId): Result<Vacation, RepositoryError> =
        jpaVacationRepository
            .toResult {
                jpaVacationRepository.findByIdOrNull(id)
            }.toErrorIfNull {
                NotFoundError("Vacation with id: $id not found", "Vacation")
            }
}
