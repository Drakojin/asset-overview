package assetoverview.business

import jdk.nashorn.internal.runtime.regexp.joni.Config.log


object ProjectManager {
    lateinit var repository: ProjectRepository

    fun getProjects(): List<Project> {
        return findAllProjectsWhere { !it.legacy }
    }

    fun getLegacyProjects(): List<Project> {
        return findAllProjectsWhere { it.legacy }
    }

    fun getProject(id: String): Project {
       return repository.findById(id) ?: throw NotFoundException(id)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(e: NotFoundException) {
        log.warn("could not find project with ID <{}>", e.id, e)
    }

    class NotFoundException(val id: String) : RuntimeException()
    private fun findAllProjectsWhere(filter: (Project) -> Boolean) = repository.findAll().filter(filter).sortedBy { it.name }
}
