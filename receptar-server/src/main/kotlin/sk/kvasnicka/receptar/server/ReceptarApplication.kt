package sk.kvasnicka.receptar.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReceptarApplication

fun main(args: Array<String>) {
	runApplication<ReceptarApplication>(*args)
}
