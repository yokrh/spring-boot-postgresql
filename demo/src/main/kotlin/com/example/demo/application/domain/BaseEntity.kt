package com.example.demo.application.domain

import java.io.Serializable
import java.time.LocalDateTime

open class BaseEntity : Serializable {
    open val createdAt: LocalDateTime? = null
    open val updatedAt: LocalDateTime? = null
    open val createdBy: String? = null
    open val updatedBy: String? = null
    val version: Int? = null
}