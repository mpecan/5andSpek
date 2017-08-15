package si.pecan.five.and.spek.service

import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import si.pecan.five.and.spek.spring.injector

class HelloWorldServiceTest : Spek({
    val injector = injector()
    val test by injector(HelloWorldService::class.java)

    it("should correctly display Hello World!") {
        expect(test.helloWorld(null)).to.equal("Hello, World!")
    }
})


