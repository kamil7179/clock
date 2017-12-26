package pl.juido.github.service

import org.springframework.stereotype.Service
import pl.juido.github.model.RepositoryDetails
import pl.juido.github.service.dto.RepositoryDetailsDTO

@Service
class RepositoryService(private val gitHubClient: GitHubClient) {
    fun getRepositoryDetails(owner: String, repositoryName: String) =
            gitHubClient
                    .getRepositoryDetails(owner, repositoryName)
                    ?.let { repositoryDetailsDtoToRepositoryDetailsMapper(it) }

    fun repositoryDetailsDtoToRepositoryDetailsMapper(repositoryDetailsDTO: RepositoryDetailsDTO) =
            RepositoryDetails(repositoryDetailsDTO.full_name, repositoryDetailsDTO.description, repositoryDetailsDTO.clone_url, repositoryDetailsDTO.stargazers_count, repositoryDetailsDTO.created_at)

}