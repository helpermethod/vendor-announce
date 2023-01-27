package io.sdkman.routes

import io.ktor.resources.Resource

@Resource("/announce")
class Announce {
    @Resource("/struct")
    class Struct(val parent: Announce = Announce())
}
