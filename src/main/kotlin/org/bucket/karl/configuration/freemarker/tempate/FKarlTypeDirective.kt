package org.bucket.karl.configuration.freemarker.tempate

import freemarker.core.Environment
import freemarker.template.*
import org.bucket.karl.configuration.freemarker.KarlFreemarkerProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FKarlTypeDirective : TemplateDirectiveModel {
    @Autowired
    protected lateinit var properties: KarlFreemarkerProperties

    override fun execute(env: Environment?, params: MutableMap<Any?, Any?>?, loopVars: Array<out TemplateModel>?, body: TemplateDirectiveBody?) {
        val out = env!!.out
        val value = params!!["value"]
        value ?: return
        val type = params["setType"]
        type ?: return
        val valueModel = value as SimpleNumber
        val setTypeModel = type as SimpleScalar
        val setTypes = properties.settings?.get(setTypeModel.asString) as Map<String, String>
        val text = setTypes[valueModel.toString()]
        out.write(text)
    }
}