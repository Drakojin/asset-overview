package assetoverview.persistence

import assetoverview.business.Project
import assetoverview.business.ProjectRepository
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import java.nio.file.Files.list
import java.nio.file.Path
import java.util.stream.Collectors.toList

internal class GitProjectRepository(
        private val localRepository: LocalGitRepository,
        private val repositoryUrl: String?
): ProjectRepository {

    private val objectMapper: ObjectMapper = ObjectMapper()
    private val fileSuffixFilter = { file: File -> file.name.endsWith(".project") }

    init {
        with(localRepository) {
            setOriginRemote(repositoryUrl!!)
            fetchOrigin()
            resetToOriginMaster()
        }
    }

    override fun findAll(): List<Project> {
        val workingDirectory = localRepository.getWorkingDirectory()
        return list(workingDirectory)
                .map(Path::toFile)
                .filter(File::isFile)
                .filter(fileSuffixFilter)
                .map(this::readAsProject)
                .collect(toList())
    }

    override fun findById(id: String): Project? {
        return list(localRepository.getWorkingDirectory())
                .map(Path::toFile)
                .filter(File::isFile)
                .filter(fileSuffixFilter)
                .filter { file -> id == file.nameWithoutExtension }
                .map(this::readAsProject)
                .findFirst().orElse(null)
    }

    override fun refresh() {
        with(localRepository) {
            fetchOrigin();
            resetToOriginMaster()
        }
    }

    fun readAsProject(projectFile: File): Project {
        return objectMapper.readValue(projectFile, Project::class.java)
                .apply { id = projectFile.nameWithoutExtension }
    }
}
