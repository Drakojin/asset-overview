/*
package assetoverview.ui

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import assetoverview.business.Artifact
import assetoverview.business.Project
import assetoverview.business.ProjectRepository
import assetoverview.ui.UiController.NotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class UiControllerTest {

    val repository: ProjectRepository = mock()
    val cut: UiController = UiController(repository)

    @Test fun `get index HTML loads all projects into the model`() {
        val project = Project(id = "1")
        val anotherProject = Project(id = "2")
        val foundProjects = listOf(project, anotherProject)
        doReturn(foundProjects).whenever(repository).findAll()

        val model: Model = mock()
        val templateName = cut.getIndex(model)

        assertThat(templateName).isEqualTo("index")
        verify(model).addAttribute("projects", foundProjects)
    }

    @Test fun `get project HTML loads the project into the model`() {
        val foundProject = Project()
        doReturn(foundProject).whenever(repository).findById("projectId")

        val model: Model = mock()
        val templateName = cut.getProject("projectId", model)

        assertThat(templateName).isEqualTo("project")
        verify(model).addAttribute("project", foundProject)
    }

    @Nested inner class `render flags are set correctly` {

        val project: Project = Project()
        val model: Model = mock()

        @BeforeEach fun projectIsReturned() {
            doReturn(project).whenever(repository).findById("projectId")
        }

        @Nested inner class `documentation` {

            @Test fun `is not rendered if documentation is NULL`() {
                project.documentation = null
                cut.getProject("projectId", model)
                verify(model).addAttribute("render_documentation", false)
            }

            @Test fun `is not rendered if documentation is empty`() {
                project.documentation = ""
                cut.getProject("projectId", model)
                verify(model).addAttribute("render_documentation", false)
            }

            @Test fun `is not rendered if documentation is only whitespace`() {
                project.documentation = " "
                cut.getProject("projectId", model)
                verify(model).addAttribute("render_documentation", false)
            }

            @Test fun `is rendered if documentation has anything as its value`() {
                project.documentation = "anything else"
                cut.getProject("projectId", model)
                verify(model).addAttribute("render_documentation", true)
            }

        }

        @Nested inner class `continuous integration table` {

            @Test fun `is not rendered if there are no branches`() {
                project.branches = mutableListOf()
                cut.getProject("projectId", model)
                verify(model).addAttribute("render_ci", false)
            }

            @Test fun `is rendered when there is at least one branch`() {
                project.branches = mutableListOf("master")
                cut.getProject("projectId", model)
                verify(model).addAttribute("render_ci", true)
            }

        }

        @Nested inner class `artifacts table` {

            @Test fun `is not rendered if there are no artifacts`() {
                project.artifacts = mutableListOf()
                cut.getProject("projectId", model)
                verify(model).addAttribute("render_artifacts", false)
            }

            @Test fun `is rendered when there is at least one artifact`() {
                project.artifacts = mutableListOf(Artifact())
                cut.getProject("projectId", model)
                verify(model).addAttribute("render_artifacts", true)
            }

        }

        @ParameterizedTest(name = "{0}")
        @ValueSource(strings = arrayOf("travis", "codecov", "bettercode"))
        fun `continuous integration service is rendered if configured`(serviceId: String) {
            project.services = mutableListOf()
            cut.getProject("projectId", model)
            verify(model).addAttribute("render_$serviceId", false)
        }

        @ParameterizedTest(name = "{0}")
        @ValueSource(strings = arrayOf("travis", "codecov", "bettercode"))
        fun `continuous integration service is not rendered if absent`(serviceId: String) {
            project.services = mutableListOf(serviceId)
            cut.getProject("projectId", model)
            verify(model).addAttribute("render_$serviceId", true)
        }

    }

    @Test fun `get project HTML throws exception if project is not found`() {
        doReturn(null).whenever(repository).findById("projectId")
        assertThrows(NotFoundException::class.java, {
            cut.getProject("projectId", mock())
        }).also { e ->
            assertThat(e.id).isEqualTo("projectId")
        }
    }

}
*/
