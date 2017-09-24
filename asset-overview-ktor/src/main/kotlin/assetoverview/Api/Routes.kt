package assetoverview.Api

import org.jetbrains.ktor.locations.location

@location("/")
class Index()

@location("/{id}")
data class ViewProject(val id: String)
