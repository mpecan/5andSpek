package si.pecan.five.and.spek.functional

import org.springframework.context.support.beans
import si.pecan.five.and.spek.service.HelloWorldService
import si.pecan.five.and.spek.web.HelloWorldHandler

fun beans() = beans {
    bean<HelloWorldService>()
    bean {
        HelloWorldHandler(it.ref())
    }
    bean {
        rootRouter(it.ref())
    }
}

class Foo
