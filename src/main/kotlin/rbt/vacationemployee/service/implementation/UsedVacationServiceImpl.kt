@file:Suppress("ktlint:standard:no-wildcard-imports")

package rbt.vacationemployee.service.implementation

import com.github.michaelbull.result.*
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import rbt.vacationemployee.domain.UsedVacation
import rbt.vacationemployee.domain.VacationId
import rbt.vacationemployee.dto.UsedVacationModel
import rbt.vacationemployee.dto.UsedVacationResponse
import rbt.vacationemployee.dto.toResponse
import rbt.vacationemployee.dto.toUsedVacation
import rbt.vacationemployee.error.Error
import rbt.vacationemployee.repository.UsedVacationRepository
import rbt.vacationemployee.repository.VacationRepository
import rbt.vacationemployee.service.UsedVacationService
import java.time.LocalDate

@Service
@Transactional
class UsedVacationServiceImpl(
    private val vacationRepository: VacationRepository,
    private val usedVacationRepository: UsedVacationRepository,
) : UsedVacationService {
    override fun searchByFromToDate(
        email: String,
        fromDate: LocalDate,
        toDate: LocalDate,
    ): Result<List<UsedVacationResponse>, Error> =
        usedVacationRepository
            .findUsedVacationsByFromAndToDate(
                email,
                fromDate,
                toDate,
            ).map { list ->
                list.map(UsedVacation::toResponse)
            }

    override fun addUsedVacation(usedVacationModel: UsedVacationModel): Result<UsedVacationResponse, Error> =
        vacationRepository.findVacationById(VacationId(usedVacationModel.email, usedVacationModel.year)).andThen { v ->
            usedVacationRepository.save(usedVacationModel.toUsedVacation(v)).map { uv ->
                uv.toResponse()
            }
        }
}
