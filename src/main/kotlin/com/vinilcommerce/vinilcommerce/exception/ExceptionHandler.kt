package com.vinilcommerce.vinilcommerce.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(value = [NotFoundException::class, IllegalArgumentException::class])
    fun handleNotFound(response: HttpServletResponse){
        response.sendError(HttpStatus.NOT_FOUND.value())
    }
}