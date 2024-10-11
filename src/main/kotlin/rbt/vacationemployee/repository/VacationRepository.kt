package rbt.vacationemployee.repository

import com.github.michaelbull.result.Result
import rbt.vacationemployee.domain.Vacation
import rbt.vacationemployee.domain.VacationId
import rbt.vacationemployee.error.RepositoryError

interface VacationRepository {
    fun findVacationById(id: VacationId): Result<Vacation, RepositoryError>
}
