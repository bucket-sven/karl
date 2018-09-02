package org.bucket.karl.configuration.shiro

import org.apache.shiro.mgt.DefaultSecurityManager
import org.apache.shiro.spring.LifecycleBeanPostProcessor
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.DependsOn

class ShiroManager {

    @Bean("lifecycleBeanPostProcessor")
    @ConditionalOnMissingBean
    fun lifecycleBeanPostProcessor(): LifecycleBeanPostProcessor {
        return LifecycleBeanPostProcessor()
    }

    @Bean("defaultAdvisorAutoProxyCreator")
    @ConditionalOnMissingBean
    @DependsOn("lifecycleBeanPostProcessor")
    fun defaultAdvisorAutoProxyCreator(): DefaultAdvisorAutoProxyCreator {
        val defaultAdvisorAutoProxyCreator = DefaultAdvisorAutoProxyCreator()
        defaultAdvisorAutoProxyCreator.isProxyTargetClass = true
        return defaultAdvisorAutoProxyCreator
    }

    @Bean
    @ConditionalOnMissingBean
    fun getAuthorizationAttributeSourceAdvisor(securityManager: DefaultSecurityManager): AuthorizationAttributeSourceAdvisor {
        val aasa = AuthorizationAttributeSourceAdvisor()
        aasa.securityManager = securityManager
        return aasa
    }
}