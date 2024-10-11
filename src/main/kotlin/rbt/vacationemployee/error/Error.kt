package rbt.vacationemployee.error

sealed interface Error

sealed interface RepositoryError : Error {
    val message: String
}

data class NotFoundError(
    override val message: String,
    val table: String,
) : RepositoryError

sealed interface DatabaseError : RepositoryError {
    val cause: Throwable
}

data class TransientError(
    override val message: String,
    override val cause: Throwable,
) : DatabaseError

data class NonTransientError(
    override val message: String,
    override val cause: Throwable,
) : DatabaseError
