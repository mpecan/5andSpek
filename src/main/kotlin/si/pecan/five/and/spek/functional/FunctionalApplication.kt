package si.pecan.five.and.spek.functional

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.function.server.router
import si.pecan.five.and.spek.web.HelloWorldHandler

@SpringBootApplication
@EnableWebFlux
class FunctionalApplication




fun rootRouter(helloWorldHandler: HelloWorldHandler) = router {
        GET("/hello_world", helloWorldHandler::handle)
}

fun main(args: Array<String>) {
    SpringApplication.run(FunctionalApplication::class.java)
}
