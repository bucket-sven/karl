package org.bucket.karl.manage.entity

import org.bucket.karl.basic.entity.BaseEntity
import javax.persistence.*

@Entity(name = "sys_role")
class Role : BaseEntity() {
    var name: String? = null
    var description: String? = null

    @ManyToMany(mappedBy = "roles")
    var admins: Set<Admin>? = null

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_role_function", joinColumns = [(JoinColumn(name = "role_id"))], inverseJoinColumns = [(JoinColumn(name = "function_id"))])
    var functions: Set<Function>? = null
}