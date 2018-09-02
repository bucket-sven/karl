package org.bucket.karl.manage.entity

import org.bucket.karl.basic.entity.BaseEntity
import org.bucket.karl.basic.entity.User
import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet

@Entity
@Table(name = "sys_admin")
class Admin : BaseEntity() {
    @Column
    var loginTime: Date? = null
    @Column
    var loginCount: Int? = null

    @OneToOne(cascade = [ CascadeType.ALL ], fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User? = null

    @OneToOne(cascade = [ CascadeType.ALL ], fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member? = null

    @ManyToMany(cascade = [ CascadeType.ALL ], fetch = FetchType.LAZY)
    @JoinTable(name = "sys_admin_role", joinColumns = [(JoinColumn(name = "admin_id"))], inverseJoinColumns = [ (JoinColumn(name = "role_id"))])
    var roles: MutableSet<Role>? = null

    @Column
    var locked: Int? = null

    val userName: String?
        get() = user?.username

    val loginName: String?
        get() = user?.username

    fun addToRole(role: Role) {
        if (roles != null) {
            roles!!.add(role)
        } else {
            val h = HashSet<Role>()
            h.add(role)
            roles = h
        }
    }

    override fun equals(obj: Any?): Boolean {
        if (null == obj) return false
        if (obj !is Admin) return false
        val admin = obj as Admin?
        if (null == this.id || null == admin!!.id)
            return false
        return this.id == admin.id
    }
}