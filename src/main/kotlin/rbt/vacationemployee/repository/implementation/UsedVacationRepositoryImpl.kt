package rbt.vacationemployee.repository.implementation

import com.github.michaelbull.result.Result
import org.springframework.stereotype.Component
import rbt.vacationemployee.domain.UsedVacation
import rbt.vacationemployee.error.RepositoryError
import rbt.vacationemployee.extensions.toResult
import rbt.vacationemployee.repository.UsedVacationRepository
import rbt.vacationemployee.repository.jpa.JpaUsedVacationRepository
import java.time.LocalDate

@Component
class UsedVacationRepositoryImpl(
    private val jpaUsedVacationRepository: JpaUsedVacationRepository,
) : UsedVacationRepository {
    override fun save(usedVacation: UsedVacation): Result<UsedVacation, RepositoryError> =
        jpaUsedVacationRepository.toResult {
            jpaUsedVacationRepository.save(usedVacation)
        }

    override fun findUsedVacationsByFromAndToDate(
        email: String,
        fromDate: LocalDate,
        toDate: LocalDate,
    ): Result<List<UsedVacation>, RepositoryError> =
        jpaUsedVacationRepository.toResult {
            jpaUsedVacationRepository.findUsedVacationsByFromAndToDate(email, fromDate, toDate)
        }
}
