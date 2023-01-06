package com.example.demo.application.domain.batch.job

import com.example.demo.application.domain.batch.BatchResult
import org.springframework.stereotype.Service

@Service
class ImportBatchJob: BatchJob {
    override val jobName: BatchJobName = BatchJobName.IMPORT_BATCH_JOB

    override fun run() {
        recordTime { execImport() }
    }

    fun execImport(): BatchResult {
        val result = BatchResult()
        var failed = false

        for (i in 1..100) {
            if (Math.random() < 0.98) {


                result.incrementSuccess()
            } else {
                result.incrementFailed()
                failed = true
            }
        }

        return result.commit(failed)
    }
}