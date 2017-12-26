package pl.juido.github.model

import java.time.LocalDateTime

data class RepositoryDetails(val fullName: String, val description: String, val cloneUrl: String, val stars: Int, val createdAt: LocalDateTime)