package org.bucket.karl

import com.alibaba.fastjson.serializer.SerializerFeature
import com.alibaba.fastjson.support.config.FastJsonConfig
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter
import org.bucket.karl.configuration.shiro.ShiroProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.servlet.Filter

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(ShiroProperties::class)
class KarlApplication : WebMvcConfigurer {
    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        super.configureMessageConverters(converters)
        val fastConverter = FastJsonHttpMessageConverter()
        val fastJsonConfig = FastJsonConfig()
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat)
        fastConverter.fastJsonConfig = fastJsonConfig
        converters.add(fastConverter)
    }

    @Bean
    fun registFiler(): FilterRegistrationBean<Filter> {
        val registration = FilterRegistrationBean<Filter>()
        registration.filter = OpenEntityManagerInViewFilter()
        registration.addUrlPatterns("/*")
        registration.order = 1
        return registration
    }
}

fun main(args: Array<String>) {
    runApplication<KarlApplication>(*args)
}