package rbt.vacationemployee.controller

import com.github.michaelbull.result.mapBoth
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import rbt.vacationemployee.dto.UsedVacationRequest
import rbt.vacationemployee.dto.toModel
import rbt.vacationemployee.error.DatabaseError
import rbt.vacationemployee.error.NotFoundError
import rbt.vacationemployee.service.UsedVacationService
import rbt.vacationemployee.utils.AuthHeaderUtil
import java.time.LocalDate

@RestController
@RequestMapping("/usedVacation")
class UsedVacationController(
    private val usedVacationService: UsedVacationService,
) {
    @GetMapping
    fun getUsedVacationsFromToDate(
        @RequestHeader("Authorization") authHeader: String,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) fromDate: LocalDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) toDate: LocalDate,
    ): ResponseEntity<Any> =
        usedVacationService
            .searchByFromToDate(
                AuthHeaderUtil.getEmailFromAuthHeader(authHeader),
                fromDate,
                toDate,
            ).mapBoth(
                { v -> ResponseEntity.ok(v) },
                { e ->
                    when (e) {
                        is DatabaseError -> ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
                        is NotFoundError -> ResponseEntity(e, HttpStatus.NOT_FOUND)
                    }
                },
            )

    @PostMapping
    fun addUsedVacation(
        @RequestHeader("Authorization") authHeader: String,
        @RequestBody usedVacationRequest: UsedVacationRequest,
    ): ResponseEntity<Any> =
        usedVacationService
            .addUsedVacation(
                usedVacationRequest.toModel(AuthHeaderUtil.getEmailFromAuthHeader(authHeader)),
            ).mapBoth(
                { v -> ResponseEntity.ok(v) },
                { e ->
                    when (e) {
                        is DatabaseError -> ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR)
                        else -> ResponseEntity(e, HttpStatus.BAD_REQUEST)
                    }
                },
            )
}
