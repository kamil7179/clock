package pl.juido.github.service

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import pl.juido.github.service.dto.RepositoryDetailsDTO
import java.time.LocalDateTime


@RunWith(MockitoJUnitRunner::class)
internal class RepositoryServiceTest {

    @InjectMocks
    private lateinit var repositoryService: RepositoryService
    @Mock
    lateinit private var gitHubClient: GitHubClient

    private var repositoryDetailsDTO: RepositoryDetailsDTO = RepositoryDetailsDTO()

    @Before
    fun init() {
        repositoryDetailsDTO.full_name = "full_name"
        repositoryDetailsDTO.clone_url = "clone_url"
        repositoryDetailsDTO.created_at = LocalDateTime.now()
        repositoryDetailsDTO.description = "description"
        repositoryDetailsDTO.stargazers_count = 1
    }

    @Test
    fun should() {
        `when`(gitHubClient.getRepositoryDetails(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(repositoryDetailsDTO)
        val repositoryDetails = repositoryService.getRepositoryDetails("maxpou", "docker-symfony")
        assertNotNull(repositoryDetails)
        assertEquals(repositoryDetails!!.fullName, repositoryDetailsDTO.full_name)
        assertEquals(repositoryDetails.cloneUrl, repositoryDetailsDTO.clone_url)
        assertEquals(repositoryDetails.createdAt, repositoryDetailsDTO.created_at)
        assertEquals(repositoryDetails.description, repositoryDetailsDTO.description)
        assertEquals(repositoryDetails.stars, repositoryDetailsDTO.stargazers_count)
    }
}