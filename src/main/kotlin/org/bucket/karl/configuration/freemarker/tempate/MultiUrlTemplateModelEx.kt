package org.bucket.karl.configuration.freemarker.tempate

import freemarker.template.TemplateMethodModelEx
import freemarker.template.TemplateModelException
import org.bucket.karl.basic.utils.Constants

class MultiUrlTemplateModelEx : TemplateMethodModelEx {
    var host: String? = null
    var isNowFresh = false
    var suffix: String? = null

    @Throws(TemplateModelException::class)
    override fun exec(arguments: MutableList<Any?>?): Any {
        if (null == arguments || arguments.size == 0) return ""
        var url = arguments[0]?.toString() ?: ""
        if (url.isBlank()) {
            return ""
        }

        if (!host!!.endsWith("/")) {
            host = host!! + "/"
        }

        if (url.startsWith("/")) {
            url = url.substring(1)
        }

        if (!suffix!!.startsWith(".")) {
            suffix = "." + suffix!!
        }

        val result = StringBuffer()
        result.append(host).append(url).append(suffix)
        if (isNowFresh) {
            result.append("?t=").append(Constants.RESET_TIME)
        }
        return result.toString()
    }
}