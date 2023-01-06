package com.example.demo.application.repository.product.mapper

import com.example.demo.application.domain.product.ProductEntity
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ProductMapper {
    fun insertProduct(product: ProductEntity)
    fun insertProductList(productList: List<ProductEntity>)
    fun getProduct(id: Long): ProductEntity
}
