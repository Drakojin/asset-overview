package assetoverview.Ui

import org.jetbrains.ktor.content.resolveResource
import org.jetbrains.ktor.locations.get
import org.jetbrains.ktor.locations.location
import org.jetbrains.ktor.response.respond
import org.jetbrains.ktor.routing.Route

@location("/styles/main.css")
class MainCss()

fun Route.styles() {
    get<MainCss> {
        call.respond(call.resolveResource("css/style.css")!!)
    }
}
