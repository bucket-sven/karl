package org.bucket.karl.manage.controller

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.*
import org.apache.shiro.crypto.hash.Sha256Hash
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping("/login")
class LoginController {
    val logger = LoggerFactory.getLogger(LoginController::class.java)

    @RequestMapping("", "index")
    fun loginView(): String {
        return "authorization/login"
    }

    @RequestMapping("/submit", method = [ RequestMethod.POST ])
    fun submit(model: Model, username: String, password: String): String {
        try {
            val subject = SecurityUtils.getSubject()
            val passwordHash = Sha256Hash(password).toHex()
            val token = UsernamePasswordToken(username, passwordHash)
            subject.login(token)
        } catch (e: UnknownAccountException) {
            logger.error("UnknownAccountException", e)
            model.addAttribute("error", "帐号不存在")
            return "authorization/login"
        } catch (e: IncorrectCredentialsException) {
            logger.error("IncorrectCredentialsException", e)
            model.addAttribute("error", "不合法的证书")
            return "authorization/login"
        } catch (e: LockedAccountException) {
            model.addAttribute("error", "帐号已锁定")
            logger.error("LockedAccountException", e)
            return "authorization/login"
        } catch (e: AuthenticationException) {
            model.addAttribute("error", "认证失败")
            logger.error("AuthenticationException", e)
            return "authorization/login"
        }
        return "redirect:/index.html"
    }
}