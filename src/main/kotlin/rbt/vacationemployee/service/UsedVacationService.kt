package rbt.vacationemployee.service

import com.github.michaelbull.result.Result
import rbt.vacationemployee.dto.UsedVacationModel
import rbt.vacationemployee.dto.UsedVacationResponse
import rbt.vacationemployee.error.Error
import java.time.LocalDate

interface UsedVacationService {
    fun searchByFromToDate(
        email: String,
        fromDate: LocalDate,
        toDate: LocalDate,
    ): Result<List<UsedVacationResponse>, Error>

    fun addUsedVacation(usedVacationModel: UsedVacationModel): Result<UsedVacationResponse, Error>
}
