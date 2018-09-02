package org.bucket.karl.basic.controller

import org.bucket.karl.configuration.freemarker.editor.DateEditor
import org.springframework.beans.propertyeditors.StringTrimmerEditor
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import java.util.*

open class BaseController {
    @InitBinder
    fun initBinder(webDataBinder: WebDataBinder) {
        webDataBinder.registerCustomEditor(String::class.java, StringTrimmerEditor(true))
        webDataBinder.registerCustomEditor(Date::class.java, DateEditor(true))
    }
}