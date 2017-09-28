package assetoverview.business


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

    private fun findAllProjectsWhere(filter: (Project) -> Boolean) = repository.findAll().filter(filter).sortedBy { it.name }

    class NotFoundException(val id: String) : RuntimeException()
}
