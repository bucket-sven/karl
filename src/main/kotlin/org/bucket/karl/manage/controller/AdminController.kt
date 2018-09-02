package org.bucket.karl.manage.controller

import org.bucket.karl.basic.controller.BaseController
import org.bucket.karl.manage.entity.Admin
import org.bucket.karl.manage.service.IAdminService
import org.bucket.karl.manage.service.IRoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin")
class AdminController : BaseController() {
    @Autowired
    protected lateinit var adminService: IAdminService
    @Autowired
    protected lateinit var roleService: IRoleService

    @RequestMapping("", "index")
    fun index(model: Model): String {
        model.addAttribute("admins", adminService.findAll())
        return "admin/index"
    }

    @RequestMapping("/remove")
    fun remove(id: Long): String {
        adminService.delete(id)
        return "redirect:index.html"
    }

    @RequestMapping("/oauth")
    fun oauth(id: Long, model: Model): String {
        model.addAttribute("admin", adminService.find(id))
        model.addAttribute("roles", roleService.findAll())
        return "authorization/userIndex"
    }

    @RequestMapping("/oauthsubmit")
    fun oauthsubmit(id: Long, roleIds: Array<Long>): String {
        adminService.saveAdminRoles(id, roleIds)
        return "redirect:index.html"
    }

    @RequestMapping("/saveOrUpdate")
    fun saveOrUpdate(admin: Admin): String {
        adminService.saveOrUpdate(admin)
        return "redirect:index.html"
    }
}