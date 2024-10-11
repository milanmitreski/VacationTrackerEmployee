package rbt.vacationemployee.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import rbt.vacationemployee.domain.Vacation
import rbt.vacationemployee.domain.VacationId

interface JpaVacationRepository : JpaRepository<Vacation, VacationId>
