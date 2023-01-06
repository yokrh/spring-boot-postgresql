package com.example.demo

import com.example.demo.application.domain.batch.job.BatchJobName
import com.example.demo.application.domain.batch.BatchSelector
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.ZoneOffset
import java.util.TimeZone
import kotlin.system.exitProcess

@SpringBootApplication
class DemoApplication: CommandLineRunner {
	private val log = LoggerFactory.getLogger(javaClass)

	@Autowired
	lateinit var selector: BatchSelector

	@Value("\${batch.job-name}")
	lateinit var batchJobName: BatchJobName

	override fun run(vararg args: String?) {
		log.info("-- batch start [${batchJobName}] --")

		selector.select(batchJobName).run()

		log.info("-- batch end [${batchJobName}] --")
	}
}

fun main(args: Array<String>) {
	exitProcess(
		SpringApplication.exit(
			runApplication<DemoApplication>(*args) {
				TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC))
			}
		)
	)
}
