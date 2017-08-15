package si.pecan.five.and.spek.controller

import org.hamcrest.core.Is.`is`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import si.pecan.five.and.spek.spring.SpringTestMockMvc
import si.pecan.five.and.spek.spring.injector

class HelloWorldControllerTests : Spek({
    val injector = injector(SpringTestMockMvc::class.java)
    val mockMvc by injector(MockMvc::class.java)


    it("Should retrieve Hello World through MockMvc"){
        mockMvc.perform(get("/hello/world"))
                .andExpect(status().isOk)
                .andExpect(content().string(`is`("Hello, World!")))
    }

    it("Should correctly change World to Jeff when it is passed as the name query parameter"){
        mockMvc.perform(get("/hello/world?name={name}", "Jeff"))
                .andExpect(status().isOk)
                .andExpect(content().string(`is`("Hello, Jeff!")))
    }
})

