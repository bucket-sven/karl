package org.bucket.karl.manage.service.impl

import org.apache.shiro.crypto.hash.Sha256Hash
import org.bucket.karl.basic.dao.IBaseDAO
import org.bucket.karl.basic.service.impl.BaseServiceImpl
import org.bucket.karl.manage.dao.IAdminDAO
import org.bucket.karl.manage.entity.Admin
import org.bucket.karl.manage.entity.Role
import org.bucket.karl.manage.service.IAdminService
import org.bucket.karl.manage.service.IRoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class AdminServiceImpl: BaseServiceImpl<Admin, Long>(), IAdminService {
    @Autowired
    private lateinit var adminDAO: IAdminDAO
    @Autowired
    private lateinit var roleService: IRoleService
    @Value("\${karl.freemarker.variables.default-role}")
    private var defaultUserRoleId: Long = 0

    override val baseDao: IBaseDAO<Admin, Long>
        get() = this.adminDAO

    override fun findByUsername(username: String): Admin? {
        return adminDAO.findByUsername(username)
    }

    override fun saveAdminRoles(id: Long, roles: Array<Long>): Boolean? {
        try {
            val admin = find(id)
            val rs = HashSet<Role>(roles.size)
            roles.forEach { rs.add(roleService.find(it)) }
            admin.roles = rs
            super.save(admin)
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    override fun saveOrUpdate(admin: Admin) {
        if (admin.id != null) {
            val adminOne = find(admin.id!!)
            val user = adminOne.user
            if (admin.user != null) {
                user!!.username = admin.user!!.username
                if (!admin.user?.password.isNullOrBlank()) {
                    user.password = Sha256Hash(admin.user!!.password).toHex()
                }
            }
            val member = adminOne.member
            if (member == null) {
                adminOne.member = admin.member
            } else {
                if (admin.member != null) {
                    member.birthday = admin.member!!.birthday
                    member.sex = admin.member!!.sex
                    member.name = admin.member!!.name
                }
            }
            update(adminOne)
        } else {
            if (admin.user != null) {
                if (!admin.user?.password.isNullOrBlank()) {
                    val password = Sha256Hash(admin.user?.password).toHex()
                    admin.user!!.password = password
                }
            }
            val role = roleService.find(defaultUserRoleId)
            admin.addToRole(role)
            save(admin)
        }
    }
}