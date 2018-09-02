package org.bucket.karl.configuration.freemarker

import org.bucket.karl.configuration.freemarker.tempate.FKarlTypeDirective
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer
import java.util.*
import kotlin.collections.HashMap

@Configuration
@EnableConfigurationProperties(KarlFreemarkerProperties::class, FreeMarkerProperties::class)
class FreemarkerConfig {
    @Autowired
    protected lateinit var karlTypeDirective: FKarlTypeDirective
    @Autowired
    protected lateinit var freemarkerProperties: KarlFreemarkerProperties
    @Autowired
    protected lateinit var properties: FreeMarkerProperties

    /**
     * 自定义freemarker函数，karlType已经在functions/index.ftl中使用
     */
    protected fun applyProperties(factory: FreeMarkerConfigurationFactory) {
        factory.setTemplateLoaderPaths(*properties.templateLoaderPath)
        factory.setPreferFileSystemAccess(properties.isPreferFileSystemAccess)
        factory.setDefaultEncoding(properties.charsetName)
        val settings = Properties()
        settings.putAll(properties.settings)
        factory.setFreemarkerSettings(settings)
        if (freemarkerProperties.variables != null) {
            val fvariables = HashMap<String, Any?>(freemarkerProperties.variables)
            fvariables["karlType"] = karlTypeDirective
            factory.setFreemarkerVariables(fvariables)
        }
    }

    @Bean
    fun freeMarkerConfigurer(): FreeMarkerConfigurer {
        val configurer = FreeMarkerConfigurer()
        applyProperties(configurer)
        return configurer
    }
}