package com.example.demo.application.domain.product

import com.example.demo.application.domain.BaseEntity
import lombok.NoArgsConstructor

@NoArgsConstructor
data class ProductEntity(
    val id: Long,
    val name: String,
): BaseEntity() {
    enum class Hoge {
        AAA,
        BBB
    }
}