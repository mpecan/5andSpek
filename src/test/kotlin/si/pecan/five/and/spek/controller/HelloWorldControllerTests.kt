package si.pecan.five.and.spek.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.hamcrest.core.Is.`is`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import si.pecan.five.and.spek.spring.FunctionalSpringTestConfiguration
import si.pecan.five.and.spek.spring.GenericSpringTestConfigurationWithMockMvc
import si.pecan.five.and.spek.spring.injector

class HelloWorldControllerTests: HelloWorldControllerBaseTest(GenericSpringTestConfigurationWithMockMvc::class.java)

class HelloWorldControllerFunctionalTests: HelloWorldControllerBaseTest(FunctionalSpringTestConfiguration::class.java)

abstract class HelloWorldControllerBaseTest(configClass: Class<*>) : Spek({
    val injector = injector(configClass)
    val mockMvc by injector(MockMvc::class.java)
    val objectMapper = jacksonObjectMapper()

    it("should retrieve Hello World through MockMvc") {
        mockMvc.perform(get("/hello/world"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.response").value("Hello, World!"))
    }

    it("should correctly change World to Jeff when it is passed as the name query parameter") {
        mockMvc.perform(get("/hello/world?name={name}", "Jeff"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.response").value("Hello, Jeff!"))
    }


    mapOf(
            "James" to "Hello, James!",
            "Jeff" to "Hello, Jeff!",
            "Jill" to "Hello, Jill!"
    ).forEach {
        it("should end up as \"${it.value}\" with an input of \"${it.key}\"") {
            mockMvc.perform(get("/hello/world?name={name}", it.key))
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$.response").value(it.value))
        }

        it("should correctly deserialize a post request with name \"${it.value}\""){

            mockMvc.perform(post("/hello/world")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsBytes(HelloWorldRequest(it.key))))
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("$.response").value(it.value))
        }
    }


})

