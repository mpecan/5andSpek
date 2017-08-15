package si.pecan.five.and.spek

import com.winterbe.expekt.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import si.pecan.five.and.spek.service.HelloWorldService
import si.pecan.five.and.spek.spring.injector

class TestSpringSpek : Spek({
    val injector = injector()
    val test by injector(HelloWorldService::class.java)

    it("Should fail miserably") {
        expect(test.helloWorld(null)).to.equal("Hello World!")
    }
})


