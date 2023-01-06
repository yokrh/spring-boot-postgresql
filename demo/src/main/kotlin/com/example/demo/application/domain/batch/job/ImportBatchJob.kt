package com.example.demo.application.domain.batch.job

import com.example.demo.application.domain.batch.BatchResult
import com.example.demo.application.domain.product.ProductEntity
import com.example.demo.application.repository.product.ProductRepository
import org.springframework.stereotype.Service

@Service
class ImportBatchJob(
    private val productRepository: ProductRepository
): BatchJob {
    override val jobName: BatchJobName = BatchJobName.IMPORT_BATCH_JOB

    override fun run() {
        recordTime { execImport() }
    }

    fun execImport(): BatchResult {
        val result = BatchResult()
        var failed = false

        for (i in 1..10) {
            if (Math.random() < 0.9) {

                val p = ProductEntity(
                    id = 0L, // dummy
                    name = "name${i}"
                )
                productRepository.insert(p)

                result.incrementSuccess()
            } else {
                result.incrementFailed()
                failed = true
            }
        }

        println(">>> DEBUG")
        val pid = 1L
        val productEntity = productRepository.get(pid)
        println("productEntity ${pid}")
        println("productEntity id:${productEntity.id}")
        println("productEntity name:${productEntity.name}")
        println("productEntity createdAt:${productEntity.createdAt}")
        println("productEntity createdBy:${productEntity.createdBy}")
        println("<<< DEBUG")

        return result.commit(failed)
    }
}