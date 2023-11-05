package github.com.k0dm.departmentservice.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime


@ControllerAdvice
class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(exception: ResourceNotFoundException, webRequest: WebRequest) =
        ResponseEntity(
            ErrorDetails(
                LocalDateTime.now(),
                exception.message ?: "Something went wrong",
                webRequest.getDescription(false),
                "DEPARTMENT_NOT_FOUND"
            ), HttpStatus.NOT_FOUND
        )

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(exception: Exception, webRequest: WebRequest) =
        ResponseEntity(
            ErrorDetails(
                LocalDateTime.now(),
                exception.message ?: "Something went wrong",
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"
            ), HttpStatus.INTERNAL_SERVER_ERROR
        )
}