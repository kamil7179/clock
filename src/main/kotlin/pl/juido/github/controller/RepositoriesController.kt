package pl.juido.github.controller

import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import pl.juido.github.service.RepositoryService

@RestController
class RepositoriesController(private val repositoryService: RepositoryService) {
    //TODO weryfikacja czy wszystkie znaki w owner/name które można wpisać na githubie zostaną przyjęte w url.
    @GetMapping("/repositories/{owner}/{repository-name:.*}")
    fun repositoryData(@PathVariable("owner") owner: String, @PathVariable("repository-name") repositoryName: String) =
            repositoryService.getRepositoryDetails(owner, repositoryName) ?: throw ResourceNotFoundException()
}