package com.example.demo.application.repository.product

import com.example.demo.application.domain.product.ProductEntity

interface ProductRepository {
    fun insert(product: ProductEntity)
    fun insert(productList: List<ProductEntity>)
    fun get(id: Long): ProductEntity
}