package si.pecan.five.and.spek.spring

import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.lifecycle.CachingMode
import org.jetbrains.spek.api.lifecycle.LifecycleAware
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper
import org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate
import org.springframework.test.context.support.DefaultBootstrapContext
import si.pecan.five.and.spek.functional.FunctionalApplication
import si.pecan.five.and.spek.previous.Application


@SpringBootTest(classes = arrayOf(FunctionalApplication::class))
class DefaultSpringTestConfiguration

@SpringBootTest(classes = arrayOf(Application::class))
@AutoConfigureMockMvc
class SpringTestMockMvc


class Injector(private val scope: SpecBody, testConfiguration: Class<*>? = DefaultSpringTestConfiguration::class.java) {

    private val contextBootstrapper = SpringBootTestContextBootstrapper().apply {
        bootstrapContext = DefaultBootstrapContext(testConfiguration, DefaultCacheAwareContextLoaderDelegate())
    }
    operator fun <T> invoke(clazz: Class<T>, mode: CachingMode = CachingMode.TEST): LifecycleAware<T> {
        return scope.memoized(mode) {
            val applicationContext = contextBootstrapper.buildTestContext().applicationContext
            applicationContext.getBean(clazz)
        }
    }

}


inline fun SpecBody.injector(testConfiguration: Class<*>? = null) = testConfiguration?.let { Injector(this, testConfiguration) } ?: Injector(this)
