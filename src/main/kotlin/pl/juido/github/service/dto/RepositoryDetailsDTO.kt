package pl.juido.github.service.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDateTime

@JsonIgnoreProperties(ignoreUnknown = true)
class RepositoryDetailsDTO {
    lateinit var full_name: String
    lateinit var description: String
    lateinit var clone_url: String
    var stargazers_count: Int = -1
    lateinit var created_at: LocalDateTime
}