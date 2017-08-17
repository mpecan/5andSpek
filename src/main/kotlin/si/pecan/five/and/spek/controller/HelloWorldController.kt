package si.pecan.five.and.spek.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import si.pecan.five.and.spek.service.HelloWorldService

@RestController
@RequestMapping("/hello")
class HelloWorldController(private val helloWorldService: HelloWorldService){

    @GetMapping("/world")
    @ResponseBody
    fun world(@RequestParam name: String?) = HelloWorldResponse(helloWorldService.helloWorld(name))

    @PostMapping("/world")
    @ResponseBody
    fun worldPost(@RequestBody request: HelloWorldRequest) = HelloWorldResponse(helloWorldService.helloWorld(request.name))
}

data class HelloWorldResponse(val response: String)
data class HelloWorldRequest(val name: String)
