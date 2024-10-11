package rbt.vacationemployee.controller

import com.github.michaelbull.result.mapBoth
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import rbt.vacationemployee.error.DatabaseError
import rbt.vacationemployee.error.NotFoundError
import rbt.vacationemployee.service.VacationService
import rbt.vacationemployee.utils.AuthHeaderUtil

@RestController
@RequestMapping("/vacation")
class VacationController(
    val vacationService: VacationService,
) {
    @GetMapping("/total")
    fun getVacationDays(
        @RequestHeader("Authorization") authHeader: String,
        @RequestParam year: Int,
    ): ResponseEntity<Any> =
        vacationService
            .searchVacationDays(
                AuthHeaderUtil.getEmailFromAuthHeader(authHeader),
                year,
            ).mapBoth(
                { v -> ResponseEntity.ok(v) },
                { e ->
                    when (e) {
                        is DatabaseError -> ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
                        is NotFoundError -> ResponseEntity(e, HttpStatus.NOT_FOUND)
                    }
                },
            )

    @GetMapping("/used")
    fun getVacationDaysUsed(
        @RequestHeader("Authorization") authHeader: String,
        @RequestParam email: String,
        @RequestParam year: Int,
    ): ResponseEntity<Any> =
        vacationService
            .searchVacationDaysUsed(
                AuthHeaderUtil.getEmailFromAuthHeader(authHeader),
                year,
            ).mapBoth(
                { v -> ResponseEntity.ok(v) },
                { e ->
                    when (e) {
                        is DatabaseError -> ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
                        is NotFoundError -> ResponseEntity(e, HttpStatus.NOT_FOUND)
                    }
                },
            )

    @GetMapping("/left")
    fun getVacationDaysLeft(
        @RequestHeader("Authorization") authHeader: String,
        @RequestParam email: String,
        @RequestParam year: Int,
    ): ResponseEntity<Any> =
        vacationService
            .searchVacationDaysLeft(
                AuthHeaderUtil.getEmailFromAuthHeader(authHeader),
                year,
            ).mapBoth(
                { v -> ResponseEntity.ok(v) },
                { e ->
                    when (e) {
                        is DatabaseError -> ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
                        is NotFoundError -> ResponseEntity(e, HttpStatus.NOT_FOUND)
                    }
                },
            )
}
