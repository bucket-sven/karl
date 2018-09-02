package org.bucket.karl.configuration.shiro

import org.apache.shiro.cache.CacheManager
import org.apache.shiro.cache.MemoryConstrainedCacheManager
import org.apache.shiro.mgt.DefaultSecurityManager
import org.apache.shiro.realm.Realm
import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.apache.shiro.web.mgt.DefaultWebSecurityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.Import

@Configuration
@Import(ShiroManager::class)
class ShiroAutoConfiguration {
    @Autowired
    private lateinit var properties: ShiroProperties
    @Autowired
    private lateinit var realm: Realm

    @Bean("shiroFilter")
    @DependsOn("securityManager")
    @ConditionalOnMissingBean
    fun shiroFilter(securityManager: DefaultSecurityManager): ShiroFilterFactoryBean {
        val shiroFilter = ShiroFilterFactoryBean()
        shiroFilter.securityManager = securityManager
        shiroFilter.loginUrl = properties.loginUrl
        shiroFilter.successUrl = properties.successUrl
        shiroFilter.unauthorizedUrl = properties.unauthorizedUrl
        val chainDefinitions = properties.filterChainDefinitions
        val map = hashMapOf<String, String>()
        chainDefinitions?.forEach {
            val key = it.key
            it.value.forEach {
                map[it] = key
            }
        }
        shiroFilter.filterChainDefinitionMap = map
        return shiroFilter
    }

    @Bean("shiroCacheManager")
    @ConditionalOnMissingBean
    fun cacheManager(): CacheManager {
        return MemoryConstrainedCacheManager()
    }

    @Bean
    @ConditionalOnMissingBean
    fun securityManager(): DefaultSecurityManager {
        val sm = DefaultWebSecurityManager()
//        val realmClass = properties.realm
//        val realm = BeanUtils.instantiateClass(Class.forName(realmClass)) as Realm?
        sm.setRealm(realm)
        sm.cacheManager = cacheManager()
        return sm
    }


//    @Bean("realm")
//    @DependsOn("lifecycleBeanPostProcessor")
//    @ConditionalOnMissingBean
//    fun realm(): Any? {
//        val realmClass = properties.realm
//        try {
//            return BeanUtils.instantiateClass(Class.forName(realmClass)) as Realm?
//        } catch (e: BeanInstantiationException) {
//            e.printStackTrace()
//        } catch (e: ClassNotFoundException) {
//            e.printStackTrace()
//        }
//        return null
//    }
}