package si.pecan.five.and.spek.functional

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import si.pecan.five.and.spek.controller.HelloWorldController
import si.pecan.five.and.spek.service.HelloWorldService

class FunctionalApplication : ApplicationContextAware {
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        if (GenericApplicationContext::class.java.isAssignableFrom(applicationContext::class.java)) {
            val context = applicationContext as GenericApplicationContext
            beanDefinitions().invoke(context)
        }
    }
}

fun beanDefinitions() = beans {
    bean<HelloWorldService>()
    bean { HelloWorldController(it.ref()) }
}
