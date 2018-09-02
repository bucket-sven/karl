package org.bucket.karl.manage.controller

import org.bucket.karl.basic.controller.BaseController
import org.bucket.karl.basic.json.Result
import org.bucket.karl.manage.entity.Function
import org.bucket.karl.manage.service.IFunctionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/function")
class FunctionController : BaseController() {
    @Autowired
    private lateinit var functionService: IFunctionService

    @RequestMapping("", "index")
    fun index(model: Model): String {
        model.addAttribute("functions", functionService.findAll())
        return "function/index"
    }

    @RequestMapping("/loadTreeNode")
    @ResponseBody
    fun loadTreeNode(): Result {
        return Result(true, "查询成功", functionService.loadTree())
    }

    @RequestMapping("/add")
    fun addFunction(function: Function, model: Model): String {
        functionService.save(function)
        return "redirect:index.html"
    }

    @RequestMapping("/remove")
    fun remove(id: Long, model: Model): String {
        functionService.delete(id)
        return "redirect:index.html"
    }

    @RequestMapping("/findAll")
    @ResponseBody
    fun findUserFunctions(): Result {
        return Result(true, "查询成功", functionService.findAll())
    }
}