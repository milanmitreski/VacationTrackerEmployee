package rbt.vacationemployee.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import rbt.vacationemployee.domain.Employee

interface JpaEmployeeRepository : JpaRepository<Employee, String>
