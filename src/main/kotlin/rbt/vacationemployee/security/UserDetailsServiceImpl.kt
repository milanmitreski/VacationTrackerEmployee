package rbt.vacationemployee.security

import com.github.michaelbull.result.fold
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import rbt.vacationemployee.error.NotFoundError
import rbt.vacationemployee.repository.EmployeeRepository

@Service
class UserDetailsServiceImpl(
    private val employeeRepository: EmployeeRepository,
) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails =
        employeeRepository.findEmployeeById(email).fold(
            { e -> User(email, e.password, emptySet()) },
            { f ->
                throw UsernameNotFoundException(
                    when (f) {
                        is NotFoundError -> "User not found with email: $email! Authentication failed."
                        else -> "Database error! Authentication failed."
                    },
                )
            },
        )
}
