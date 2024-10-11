package rbt.vacationemployee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
    exclude = [
        SecurityAutoConfiguration::class,
        UserDetailsServiceAutoConfiguration::class,
    ],
)
class VacationemployeeApplication

fun main(args: Array<String>) {
    runApplication<VacationemployeeApplication>(*args)
}
