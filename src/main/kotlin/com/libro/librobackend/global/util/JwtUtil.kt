package com.libro.librobackend.global.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.security.Keys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.security.SignatureException
import java.util.*

@Component
class JwtUtil(
    @Value("\${jwt.secret}")
    secret: String
) {
    private val key: Key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret))

    fun generateToken(email: String): String =
        Jwts.builder()
            .setSubject(email)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10시간
            .signWith(key)
            .compact()

    private val log: Logger = LoggerFactory.getLogger(JwtUtil::class.java)

    fun validateToken(token: String): Boolean =
        try {
            getClaims(token)
            true
        } catch (e: ExpiredJwtException) {
            log.warn("Expired JWT token: ${e.message}")
            false
        } catch (e: MalformedJwtException) {
            log.warn("Malformed JWT token: ${e.message}")
            false
        } catch (e: SignatureException) {
            log.warn("Invalid JWT signature: ${e.message}")
            false
        } catch (e: Exception) {
            log.error("Unknown JWT validation error", e)
            false
        }

    fun getEmailFromToken(token: String): String = getClaims(token).subject

    private fun getClaims(token: String): Claims =
        Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
}