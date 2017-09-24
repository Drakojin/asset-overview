package assetoverview.business


data class Project(
        var id: String? = null,
        var name: String? = null,
        var organization: String? = null,
        var repository: String? = null,
        var documentation: String? = null,
        var branches: List<String> = listOf(),
        var services: List<String> = listOf(),
        var artifacts: List<Artifact> = listOf(),
        var legacy: Boolean = false
)

data class Artifact(
        var groupId: String? = null,
        var artifactId: String? = null
)

