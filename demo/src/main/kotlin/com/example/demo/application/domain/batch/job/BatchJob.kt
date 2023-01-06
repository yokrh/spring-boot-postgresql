package com.example.demo.application.domain.batch.job

interface BatchJob {
    val jobName: BatchJobName

    fun run()
}

inline fun <T> BatchJob.recordTime(
    methodCall: () -> T
): T {
    val begin = System.currentTimeMillis()
    try {
        return methodCall()
    } finally {
        val end = System.currentTimeMillis()
        val time = end - begin
        println("Batch finished in ${time} ms")
    }
}
