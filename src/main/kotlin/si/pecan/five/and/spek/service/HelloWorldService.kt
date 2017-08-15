package si.pecan.five.and.spek.service

import org.springframework.stereotype.Component

@Component
class HelloWorldService {
    fun helloWorld(name: String?) = "Hello, ${name ?: "World"}!"
}
