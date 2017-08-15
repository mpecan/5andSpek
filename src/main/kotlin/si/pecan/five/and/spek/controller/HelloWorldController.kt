package si.pecan.five.and.spek.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import si.pecan.five.and.spek.service.HelloWorldService

@Controller
@RequestMapping("/hello")
class HelloWorldController(private val helloWorldService: HelloWorldService){

    @GetMapping("/world")
    @ResponseBody
    fun world(@RequestParam name: String?) = helloWorldService.helloWorld(name)
}
