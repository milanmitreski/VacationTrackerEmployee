package rbt.vacationemployee.extensions

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapError
import com.github.michaelbull.result.runCatching
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import rbt.vacationemployee.error.DatabaseError
import rbt.vacationemployee.error.NonTransientError
import rbt.vacationemployee.error.TransientError

fun <T, ID, V> JpaRepository<T, ID>.toResult(lambda: () -> V): Result<V, DatabaseError> =
    runCatching {
        lambda()
    }.mapError { e ->
        when (e) {
            is LockTimeoutException -> TransientError(e.message ?: "", e)
            is OptimisticLockException -> TransientError(e.message ?: "", e)
            is PessimisticLockException -> TransientError(e.message ?: "", e)
            is QueryTimeoutException -> TransientError(e.message ?: "", e)
            is TransactionRequiredException -> TransientError(e.message ?: "", e)
            else -> NonTransientError(e.message ?: "", e)
        }
    }
