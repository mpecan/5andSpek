package si.pecan.five.and.spek.web

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import si.pecan.five.and.spek.service.HelloWorldService

class HelloWorldHandler(val helloWorldService: HelloWorldService) {
    fun handle(serverRequest: ServerRequest): Mono<ServerResponse> {
        val name = serverRequest.queryParam("name").orElse("World")
        return ok().syncBody(helloWorldService.helloWorld(name))
    }
}
