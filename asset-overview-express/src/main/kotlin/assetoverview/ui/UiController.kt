package assetoverview.ui

internal class UiController() {


    /*private val log: Logger = getLogger(javaClass)

    @GetMapping("/")
    fun getIndex(model: Model): String {
        model.addAttribute("projects", findAllProjectsWhere { !it.legacy })
        model.addAttribute("legacyProjects", findAllProjectsWhere { it.legacy })
        return "index"
    }

    private fun findAllProjectsWhere(filter: (Project) -> Boolean) = repository.findAll().filter(filter).sortedBy { it.name }

    @GetMapping("/{id}")
    fun getProject(@PathVariable id: String, model: Model): String {
        val project: Project = repository.findById(id) ?: throw NotFoundException(id)

        model.addAttribute("project", project)

        model.addAttribute("render_documentation", project.documentation?.isNotBlank() ?: false)

        model.addAttribute("render_ci", project.branches.isNotEmpty())
        model.addAttribute("render_artifacts", project.artifacts.isNotEmpty())

        model.addAttribute("render_travis", project.services.contains("travis"))
        model.addAttribute("render_codecov", project.services.contains("codecov"))
        model.addAttribute("render_bettercode", project.services.contains("bettercode"))

        return "project"
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(e: NotFoundException) {
        log.warn("could not find project with ID <{}>", e.id, e)
    }

    class NotFoundException(val id: String) : RuntimeException()*/

}
