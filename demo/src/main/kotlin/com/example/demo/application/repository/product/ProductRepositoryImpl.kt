package com.example.demo.application.repository.product

import com.example.demo.application.domain.product.ProductEntity
import com.example.demo.application.repository.product.mapper.ProductMapper
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryImpl(
    private val productMapper: ProductMapper
): ProductRepository {
    override fun insert(product: ProductEntity) {
        return productMapper.insertProduct(product)
    }

    override fun insert(productList: List<ProductEntity>) {
        return productMapper.insertProductList(productList)
    }

    override fun get(id: Long): ProductEntity {
        return productMapper.getProduct(id)
    }
}
