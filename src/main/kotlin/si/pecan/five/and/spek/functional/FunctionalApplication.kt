package si.pecan.five.and.spek.functional

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import si.pecan.five.and.spek.controller.HelloWorldController
import si.pecan.five.and.spek.service.HelloWorldService

@EnableWebMvc
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
    bean {
        Jackson2ObjectMapperBuilder().modulesToInstall(KotlinModule(), Jdk8Module(), JavaTimeModule())
            .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .serializationInclusion(JsonInclude.Include.NON_NULL)
    }
}
