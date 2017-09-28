package assetoverview.controller

import assetoverview.Api.ViewProject
import assetoverview.business.Project
import assetoverview.business.ProjectManager
import kotlinx.html.*
import org.jetbrains.ktor.html.respondHtml
import org.jetbrains.ktor.locations.get
import org.jetbrains.ktor.routing.Route

private var renderTravis = false
private var renderCodeCov = false
private var renderBetterCode = false

fun Route.viewProject(manager: ProjectManager) {

    get<ViewProject> {
        val project = manager.getProject(it.id)
        val projectRepo = "https://github.com/${project.organization}/${project.repository}"
        renderTravis = project.services.contains("travis")
        renderCodeCov = project.services.contains("codecov")
        renderBetterCode = project.services.contains("bettercode")

        call.respondHtml {
            head {
                title { +"Overview of ${project.name}" }
                meta(content = "text/html; charset=UTF-8")
                styleLink("/styles/main.css")
            }
            body {
                h1 { +"${project.name}" }
                hr()
                /*GitHub Repository Link*/
                renderImageLink(projectRepo, "https://img.shields.io/badge/repository-GitHub-blue.svg")
                /*Documentation Link*/
                renderDocumentation(project)
                /*GitHub Release Badge*/
                renderImageLink("$projectRepo/releases", "https://badge.fury.io/gh/${project.organization}%2F${project.repository}.svg")
                hr()
                renderContinuesIntegration(project)
                renderArtifacts(project)
            }
        }
    }
}
private fun BODY.renderDocumentation(project: Project) {
    if (project.documentation?.isNotBlank() == true) {
        renderImageLink("${project.documentation}", "https://img.shields.io/badge/documentation-Online-green.svg")
    }
}
private fun BODY.renderContinuesIntegration(project: Project) {
    if (project.branches.isNotEmpty()) {
        h2 { +"Continuous Integration" }
        p { +"The following table shows all of the project's maintained branches / versions with build status and quality metrics." }
        renderCiTable(project)
    }
}
private fun BODY.renderArtifacts(project: Project) {
    if (project.artifacts.isNotEmpty()) {
        h2 { + "Artifacts" }
        p { + "The following table shows all of the project's available modules." }
        renderArtifactsTable(project)
    }
}
private fun BODY.renderCiTable(project: Project) {
    table {
        tr {
            th { + "Branch" }
            if (renderTravis) th { + "Build" }
            if (renderCodeCov) th { + "Coverage" }
            if (renderBetterCode) th { + "Better Code" }
        }
        project.branches.forEach {
            tr {
                td { +it }
                /*  Travis CI Build Badge */
                if (renderTravis) renderCellImageLink("https://travis-ci.org/${project.organization}/${project.repository}/branches",
                        "https://travis-ci.org/${project.organization}/${project.repository}.svg?branch=$it")
                /* Codecov Badge */
                if (renderCodeCov) renderCellImageLink("https://codecov.io/gh/${project.organization}/${project.repository}/branch/$it",
                        "https://codecov.io/gh/${project.organization}/${project.repository}/branch/$it/graph/badge.svg")
                /*Better Code Hub Badge*/
                if (renderBetterCode) renderCellImageLink("https://bettercodehub.com/results/${project.organization}/${project.repository}/",
                        "https://bettercodehub.com/edge/badge/${project.organization}/${project.repository}?branch=$it")
            }
        }
    }
}
private fun BODY.renderArtifactsTable(project: Project) {
    table {
        tr {
            th { + "Artifact" }
            th { + "Maven Central" }
            th { + "JavaDoc" }
            th { + "Dependencies" }
        }
        project.artifacts.forEach {
            tr {
                td { + "${it.artifactId}" }
                /* Maven Central Version Badge */
                renderCellImageLink("https://maven-badges.herokuapp.com/maven-central/${it.groupId}/${it.artifactId}",
                        "https://maven-badges.herokuapp.com/maven-central/${it.groupId}/${it.artifactId}/badge.svg")
                /* JavaDoc Version Badge */
                renderCellImageLink("https://www.javadoc.io/doc/'${it.groupId}/${it.artifactId}",
                        "https://www.javadoc.io/badge/${it.groupId}/${it.artifactId}.svg")
                /*Better Code Hub Badge*/
                renderCellImageLink("https://www.versioneye.com/java/${it.groupId}:${it.artifactId}/",
                        "https://www.versioneye.com/java/${it.groupId}:${it.artifactId}/badge?style=flat")
            }
        }
    }
}
private fun TR.renderCellImageLink(link: String, imageLink: String) {
    td { a { imglink(link, imageLink) } }
}
private fun BODY.renderImageLink(link: String, imageLink: String) {
    a { imglink(link, imageLink) }
}
private fun A.imglink(link: String, imageLink: String) {
    href = link
    img(src = imageLink)
}
