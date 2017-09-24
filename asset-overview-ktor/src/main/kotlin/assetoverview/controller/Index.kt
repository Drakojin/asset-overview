package assetoverview.controller

import assetoverview.Api.Index
import assetoverview.business.Project
import assetoverview.business.ProjectManager
import kotlinx.html.*
import org.jetbrains.ktor.html.respondHtml
import org.jetbrains.ktor.locations.get
import org.jetbrains.ktor.routing.Route

fun Route.index(manager: ProjectManager) {

    get<Index> {
        val projects: List<Project> = manager.getProjects()
        val legacyProjects: List<Project> = manager.getLegacyProjects()

        call.respondHtml {
            head {
                title { +"Overview of Assets" }
                meta(content = "text/html; charset=UTF-8")
                styleLink("/styles/main.css")
            }
            body {
                h1 { +"List of Current Projects:" }
                ul {
                    projectEntries(projects)
                }
                h1 { +"List of Maintenance and Legacy Projects:" }
                ul {
                    projectEntries(legacyProjects)
                }
            }
        }
    }
}

fun UL.projectEntries(projects: List<Project>) {
    projects.forEach {
        li {
            a(href = "/${it.id}")
            { +"${it.name}" }
        }
    }
}




