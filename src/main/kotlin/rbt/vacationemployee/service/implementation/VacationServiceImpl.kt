@file:Suppress("ktlint:standard:no-wildcard-imports")

package rbt.vacationemployee.service.implementation

import com.github.michaelbull.result.*
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import rbt.vacationemployee.domain.VacationId
import rbt.vacationemployee.error.Error
import rbt.vacationemployee.repository.VacationRepository
import rbt.vacationemployee.service.VacationService

@Service
@Transactional
class VacationServiceImpl(
    private val vacationRepository: VacationRepository,
) : VacationService {
    override fun searchVacationDays(
        email: String,
        year: Int,
    ): Result<Int, Error> = vacationRepository.findVacationById(VacationId(email, year)).map { v -> v.vacationDays }

    override fun searchVacationDaysUsed(
        email: String,
        year: Int,
    ): Result<Int, Error> = vacationRepository.findVacationById(VacationId(email, year)).map { v -> v.daysUsed }

    override fun searchVacationDaysLeft(
        email: String,
        year: Int,
    ): Result<Int, Error> = vacationRepository.findVacationById(VacationId(email, year)).map { v -> v.vacationDays - v.daysUsed }
}
