package assetoverview

import assetoverview.Ui.styles
import assetoverview.business.ProjectManager
import assetoverview.business.ProjectRepository
import assetoverview.controller.index
import assetoverview.controller.viewProject
import assetoverview.persistence.GitProjectRepository
import assetoverview.persistence.LocalGitRepository
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.application.log
import org.jetbrains.ktor.features.CallLogging
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.features.StatusPages
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.locations.Locations
import org.jetbrains.ktor.routing.Routing


class AssetOverview {

    fun Application.install() {

        val repoUrl = environment.config.config("git").property("repositoryUrl").getString()
        val repo: ProjectRepository = GitProjectRepository(LocalGitRepository(), repoUrl)
        val projectManager = ProjectManager.apply { repository = repo }

        install(DefaultHeaders)
        install(CallLogging)
        install(Locations)

        install(Routing) {
            styles()
            index(projectManager)
            viewProject(projectManager)
        }
        install(StatusPages) {
            exception<ProjectManager.NotFoundException> { cause ->
                call.response.status(HttpStatusCode.NotFound)
                log.warn("could not find project with ID <{}>", cause.id)
            }
        }
    }
}
