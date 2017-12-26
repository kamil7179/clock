package pl.juido.github

import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.nio.charset.Charset


//TODO przeniesienie do testów integracyjnych
@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
class IntegrationTests {
    lateinit private var mockMvc: MockMvc
    private val contentType = MediaType(MediaType.APPLICATION_JSON.type,
            MediaType.APPLICATION_JSON.subtype,
            Charset.forName("utf8"))

    @Autowired
    private lateinit var context: WebApplicationContext

    @Before
    @Throws(Exception::class)
    fun setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .build()
    }

    @Test
    fun shouldReturnStatus200AndRequiredFieldsWhenSearchingRepositoryWhichExists() {
        val user = "octokit"
        val repoName = "octokit.rb"
        mockMvc.perform(get("/repositories/$user/$repoName"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.description", CoreMatchers.equalTo("Ruby toolkit for the GitHub API")))
                .andExpect(jsonPath("$.fullName", CoreMatchers.equalTo("octokit/octokit.rb")))
                .andExpect(jsonPath("$.cloneUrl", CoreMatchers.equalTo("https://github.com/octokit/octokit.rb.git")))
                .andExpect(jsonPath("$.stars", CoreMatchers.isA(Int::class.java)))
                .andExpect(jsonPath("$.createdAt", CoreMatchers.equalTo("2009-12-10T21:41:49")))
    }

    @Test
    fun shouldReturnStatus404WhenSearchingRepositoryWhichNJotExists() {
        val user = "notExistsUser"
        val repoName = "notExistsRepo"
        mockMvc.perform(get("/repositories/$user/$repoName"))
                .andExpect(status().isNotFound)
    }
}