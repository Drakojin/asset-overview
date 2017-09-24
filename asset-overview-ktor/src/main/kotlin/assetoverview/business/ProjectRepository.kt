package assetoverview.business

interface ProjectRepository {
    fun findAll(): List<Project>
    fun findById(id: String): Project?
    fun refresh()
}
