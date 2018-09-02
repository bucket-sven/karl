package org.bucket.karl.configuration.shiro.realm

import org.apache.shiro.authc.*
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.authz.SimpleAuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection
import org.bucket.karl.basic.entity.User
import org.bucket.karl.basic.utils.Constants
import org.bucket.karl.manage.service.IAdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*

@Component("realm")
class UserRealm : AuthorizingRealm() {
    @Autowired
    protected lateinit var adminService: IAdminService

    /**
     * 登录认证
     */
    @Throws(AuthenticationException::class)
    override fun doGetAuthenticationInfo(token: AuthenticationToken?): AuthenticationInfo {
        val username = token!!.principal as String
        val admin = adminService.findByUsername(username)
        val password = String(token.credentials as CharArray)
        if (admin == null) {
            throw UnknownAccountException()
        }
        val user = admin.user ?: throw UnknownAccountException("帐号或密码不正确")
        if (password != user.password) {
            throw IncorrectCredentialsException("帐号或密码不正确")
        }
        val info = SimpleAuthenticationInfo(user, password, name)
        admin.loginTime = Date()
        admin.loginCount = (admin.loginCount ?: 0) + 1
        adminService.update(admin)

        val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
        val session = request.session
        session.setAttribute(Constants.USER_SESSION_ID, user)
        return info
    }

    /**
     * 权限认证
     */
    override fun doGetAuthorizationInfo(principals: PrincipalCollection?): AuthorizationInfo {
        val user = principals!!.primaryPrincipal as User
        val authorizationInfo = SimpleAuthorizationInfo()
        val admin = adminService.findByUsername(user.username!!)
        val roles = admin!!.roles
        val shiroPermissions = HashSet<String>()
        roles?.forEach {
            val funcs = it.functions
            if (funcs != null) {
                funcs.forEach { shiroPermissions.add(it.shiroPermission!!) }
            }
        }
        authorizationInfo.stringPermissions = shiroPermissions
        return authorizationInfo
    }

    fun clearAllCachedAuthorizationInfo() {
        authorizationCache.clear()
    }

    fun clearAllCachedAuthenticationInfo() {
        authenticationCache.clear()
    }

    fun clearAllCache() {
        clearAllCachedAuthenticationInfo()
        clearAllCachedAuthorizationInfo()
    }
}