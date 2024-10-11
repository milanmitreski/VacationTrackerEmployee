package rbt.vacationemployee.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import rbt.vacationemployee.domain.UsedVacation
import rbt.vacationemployee.domain.UsedVacationId
import java.time.LocalDate

interface JpaUsedVacationRepository : JpaRepository<UsedVacation, UsedVacationId> {
    @Query(
        "select uv from UsedVacation uv " +
            "where (uv.usedVacationId.beginDate between :fromDate and :toDate) " +
            "and  (uv.endDate between :fromDate and :toDate) " +
            "and uv.usedVacationId.vacationId.email = :email ",
    )
    fun findUsedVacationsByFromAndToDate(
        email: String,
        fromDate: LocalDate,
        toDate: LocalDate,
    ): List<UsedVacation>
}
