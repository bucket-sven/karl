package org.bucket.karl.manage.controller

import org.apache.shiro.SecurityUtils
import org.bucket.karl.basic.controller.BaseController
import org.bucket.karl.basic.utils.UserUtils
import org.bucket.karl.manage.service.IFunctionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class IndexController : BaseController() {
    @Autowired
    protected lateinit var functionService: IFunctionService

    @RequestMapping("", "index")
    fun index(model: Model): String {
        val data = functionService.loadFunctionByAdminId(UserUtils.currentUser!!.id)
        model.addAttribute("functions", data)
        return "index"
    }

    @RequestMapping("/logout")
    fun logout(): String {
        SecurityUtils.getSubject().logout()
        return "redirect:login.html"
    }

    @RequestMapping("/register")
    fun registerView(): String {
        return "/authorization/register"
    }
}