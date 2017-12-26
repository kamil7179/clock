package pl.juido.github

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class GithubApplication

fun main(args: Array<String>) {
    runApplication<GithubApplication>(*args)
}
