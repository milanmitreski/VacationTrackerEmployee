package rbt.vacationemployee.service

import com.github.michaelbull.result.Result
import rbt.vacationemployee.error.Error

interface VacationService {
    fun searchVacationDays(
        email: String,
        year: Int,
    ): Result<Int, Error>

    fun searchVacationDaysUsed(
        email: String,
        year: Int,
    ): Result<Int, Error>

    fun searchVacationDaysLeft(
        email: String,
        year: Int,
    ): Result<Int, Error>
}
