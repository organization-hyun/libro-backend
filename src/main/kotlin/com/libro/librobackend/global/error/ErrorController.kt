package com.libro.librobackend.global.error

import jakarta.servlet.http.HttpServletRequest
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.servlet.error.ErrorAttributes
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest

@RestController
class CustomErrorController(
    private val errorAttributes: ErrorAttributes
) : ErrorController {

    @RequestMapping("/error")
    fun handleError(request: HttpServletRequest): ResponseEntity<Map<String, Any>> {
        val webRequest: WebRequest = ServletWebRequest(request)
        val attributes = errorAttributes.getErrorAttributes(
            webRequest,
            ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE, ErrorAttributeOptions.Include.BINDING_ERRORS)
        )

        val status = request.getAttribute("javax.servlet.error.status_code") as? Int ?: 500
        return ResponseEntity.status(status).body(attributes)
    }

}
