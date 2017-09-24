package assetoverview.controller

import assetoverview.Api.ViewProject
import assetoverview.business.ProjectManager
import org.jetbrains.ktor.locations.get
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.Route

fun Route.viewProject(manager: ProjectManager) {

    get<ViewProject> {
        manager.getProject(it.id)

        call.respondText (it.id)
    }
}
model.addAttribute("project", project)

model.addAttribute("render_documentation", project.documentation?.isNotBlank() ?: false)

model.addAttribute("render_ci", project.branches.isNotEmpty())
model.addAttribute("render_artifacts", project.artifacts.isNotEmpty())

model.addAttribute("render_travis", project.services.contains("travis"))
model.addAttribute("render_codecov", project.services.contains("codecov"))
model.addAttribute("render_bettercode", project.services.contains("bettercode"))

return "project"
