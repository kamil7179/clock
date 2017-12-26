package pl.juido.github.service

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.ArgumentMatchers.eq
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import pl.juido.github.service.dto.RepositoryDetailsDTO
import java.time.LocalDateTime

@RunWith(MockitoJUnitRunner::class)
internal class GitHubClientTest {
    @InjectMocks
    lateinit private var gitHubService: GitHubClient
    @Mock
    lateinit private var restTemplate: RestTemplate

    private val existsRepo = RepositoryDetailsDTO()

    @Before
    fun init() {
        existsRepo.full_name = "full_name"
        existsRepo.clone_url = "clone_url"
        existsRepo.created_at = LocalDateTime.now()
        existsRepo.description = "description"
        existsRepo.stargazers_count = 1
    }

    @Test
    fun shouldReturnCorrectDataIfRepositoryExists() {
        `when`(restTemplate.getForObject(anyString(), eq(RepositoryDetailsDTO::class.java))).thenReturn(existsRepo)
        val repositoryDetailsDTO = gitHubService.getRepositoryDetails("existsUser", "existsRepo")
        assertNotNull(repositoryDetailsDTO)
        assertEquals(repositoryDetailsDTO, existsRepo)
    }

    @Test
    fun shouldReturnNullWhenThereIsNotRepository() {
        `when`(restTemplate.getForObject(anyString(), eq(RepositoryDetailsDTO::class.java))).thenThrow(RestClientException("Repo not found"))
        val repositoryDetailsDTO = gitHubService.getRepositoryDetails("notExistsUser", "notExistsRepo")
        assertNull(repositoryDetailsDTO)
    }
}