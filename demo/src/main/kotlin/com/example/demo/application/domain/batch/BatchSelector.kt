package com.example.demo.application.domain.batch

import com.example.demo.application.domain.batch.job.BatchJob
import com.example.demo.application.domain.batch.job.BatchJobName
import com.example.demo.application.domain.batch.job.ExportBatchJob
import com.example.demo.application.domain.batch.job.ImportBatchJob
import org.springframework.stereotype.Component

@Component
class BatchSelector(
    private val importBatchJob: ImportBatchJob,
    private val exportBatchJob: ExportBatchJob
) {
    fun select(batchJobName: BatchJobName): BatchJob {
        return when (batchJobName) {
            BatchJobName.IMPORT_BATCH_JOB -> {
                importBatchJob
            }
            BatchJobName.EXPORT_BATCH_JOB -> {
                exportBatchJob
            }
        }
    }
}