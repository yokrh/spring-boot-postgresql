package com.example.demo.application.domain.batch

import org.slf4j.LoggerFactory
import java.util.concurrent.atomic.AtomicInteger

data class BatchResult(
    val batchFailed: Boolean = false,
    val successCount: Int = 0,
    val failedCount: Int = 0
) {
    private val log = LoggerFactory.getLogger(javaClass)

    private val successAtomicInteger = AtomicInteger(0)
    private val failedAtomicInteger = AtomicInteger(0)

    fun incrementSuccess(count: Int = 1) {
        successAtomicInteger.addAndGet(count)
    }

    fun incrementFailed(count: Int = 1) {
        failedAtomicInteger.addAndGet(count)
    }

    fun commit(batchFailed: Boolean): BatchResult {
        log.info("-- batchFailed: ${batchFailed}")
        log.info("-- success: ${successAtomicInteger.get()}")
        log.info("-- failed: ${failedAtomicInteger.get()}")

        return BatchResult(
            batchFailed,
            successAtomicInteger.get(),
            failedAtomicInteger.get()
        )
    }
}