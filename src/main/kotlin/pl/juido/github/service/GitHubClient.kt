package pl.juido.github.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import pl.juido.github.service.dto.RepositoryDetailsDTO


@Service
//TODO dodanie logowania do github API
class GitHubClient(private val restTemplate: RestTemplate) {
    fun getRepositoryDetails(owner: String, repositoryName: String): RepositoryDetailsDTO? =
            try {
                restTemplate.getForObject("https://api.github.com/repos/$owner/$repositoryName", RepositoryDetailsDTO::class.java)
            } catch (e: RestClientException) {
                null
            }
}