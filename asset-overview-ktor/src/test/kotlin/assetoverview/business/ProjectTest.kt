package assetoverview.business

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


internal class ProjectTest {

    @Test fun `can be (de)serialized as JSON`() {
        val mapper = ObjectMapper()
        val project = Project().apply {
            id = "id"
            name = "name"
            organization = "organization"
            repository = "repository"
            documentation = "documentation"
            branches = listOf(
                    "branch1",
                    "branch2"
            )
            services = listOf(
                    "service1",
                    "service2"
            )
            artifacts = listOf(
                    Artifact().apply {
                        artifactId = "artifactId1"
                        groupId = "groupId1"
                    },
                    Artifact().apply {
                        artifactId = "artifactId2"
                        groupId = "groupId2"
                    }
            )
        }
        val json = mapper.writeValueAsString(project)
        val fromJson = mapper.readValue(json, Project::class.java)
        assertThat(project).isEqualToComparingFieldByField(fromJson)
    }

}
