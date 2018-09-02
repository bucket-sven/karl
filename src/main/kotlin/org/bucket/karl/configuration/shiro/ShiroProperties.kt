package org.bucket.karl.configuration.shiro

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "shiro")
class ShiroProperties {
    var realm: String? = null
    var loginUrl: String? = null
    var successUrl: String? = null
    var unauthorizedUrl: String? = null
    var filterChainDefinitions: Map<String, Array<String>>? = null
    var ehacheConfig: String? = null
}