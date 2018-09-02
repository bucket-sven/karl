package org.bucket.karl.configuration.freemarker

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "karl.freemarker")
class KarlFreemarkerProperties {
    var settings: Map<String, Map<String, String>>? = null
    var variables: Map<String, String>? = null
}