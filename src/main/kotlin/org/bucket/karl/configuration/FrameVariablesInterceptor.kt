package org.bucket.karl.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@EnableConfigurationProperties(FrameVariablesInterceptor::class)
@ConfigurationProperties(prefix = "karl.frame")
class FrameVariablesInterceptor : HandlerInterceptorAdapter() {
    // 通过配置注解
    var variables: Map<String, String>? = null

    @Throws(Exception::class)
    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        if (modelAndView != null && variables != null && !variables!!.isEmpty()) {
            val keySet = variables!!.keys
            keySet.forEach { modelAndView.addObject(it, variables!![it]) }
        }
    }
}