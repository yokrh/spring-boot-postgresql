package com.example.demo.application.domain.batch.job

import com.example.demo.application.domain.batch.BatchResult
import org.springframework.stereotype.Service

@Service
class ExportBatchJob: BatchJob {
    override val jobName: BatchJobName = BatchJobName.EXPORT_BATCH_JOB

    override fun run() {
        recordTime { execExport() }
    }

    fun execExport(): BatchResult {
        val result = BatchResult()
        var failed = false

        for (i in 1..100) {
            if (Math.random() < 0.99) {
                result.incrementSuccess()
            } else {
                result.incrementFailed()
                failed = true
            }
        }

        return result.commit(failed)
    }
}