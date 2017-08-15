package si.pecan.five.and.spek.web

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import si.pecan.five.and.spek.service.HelloWorldService
import si.pecan.five.and.spek.spring.FunctionalSpringTestConfiguration
import si.pecan.five.and.spek.spring.injector


class HelloWorldHandlerTest : Spek({
    val injector = injector(FunctionalSpringTestConfiguration::class.java)
    val service by injector(HelloWorldService::class.java)

    it("Should handle requests coming to /hello_world") {

    }
})
