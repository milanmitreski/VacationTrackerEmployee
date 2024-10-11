package rbt.vacationemployee.repository

import com.github.michaelbull.result.Result
import rbt.vacationemployee.domain.UsedVacation
import rbt.vacationemployee.error.RepositoryError
import java.time.LocalDate

interface UsedVacationRepository {
    fun save(usedVacation: UsedVacation): Result<UsedVacation, RepositoryError>

    fun findUsedVacationsByFromAndToDate(
        email: String,
        fromDate: LocalDate,
        toDate: LocalDate,
    ): Result<List<UsedVacation>, RepositoryError>
}
