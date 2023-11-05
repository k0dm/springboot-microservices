package github.com.k0dm.departmentservice.exception

import java.time.LocalDateTime

data class ErrorDetails(
    val timestamp: LocalDateTime,
    val message: String,
    val path: String,
    val errorCode: String
)
