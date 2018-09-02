package org.bucket.karl.manage.controller

import org.bucket.karl.basic.json.Result
import org.bucket.karl.manage.entity.Role
import org.bucket.karl.manage.service.IRoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/role")
class RoleController {
    @Autowired
    private lateinit var roleService: IRoleService

    @RequestMapping("", "/index")
    fun index(model: Model): String {
        model.addAttribute("roles", roleService.findAll())
        return "role/index"
    }

    @RequestMapping("/remove")
    fun remove(id: Long): String {
        roleService.delete(id)
        return "redirect:index.html"
    }

    @RequestMapping("/oauth")
    fun oauth(id: Long, model: Model): String {
        model.addAttribute("role", roleService.find(id))
        return "authorization/roleIndex"
    }

    @RequestMapping("/loadFunctionTree")
    @ResponseBody
    fun function(id: Long, model: Model): Result {
        return Result(true, "success", roleService.loadTreeAndMarkRoleFunctions(id))
    }

    @RequestMapping("/oauthsubmit")
    @ResponseBody
    fun oauthsubmit(@RequestBody role: Role): Result {
        return Result(roleService.saveRoleFunctions(role.id!!, role.functions), "success")
    }

    @RequestMapping("/saveOrUpdate")
    fun saveOrUpdate(role: Role): String {
        roleService.saveOrUpdate(role)
        return "redirect:index.html"
    }
}