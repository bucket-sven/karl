package org.bucket.karl.basic.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity

@Entity(name = "sys_user")
class User : BaseEntity() {
    @Column(nullable = false, updatable = false, unique = true)
    var username: String? = null
    @Column(nullable = false)
    var password: String? = null

    @Column
    var lastLoginTime: Date? = null
    @Column
    var loginIp: String? = null

    override fun hashCode(): Int {
        val prime = 31
        var result = super.hashCode()
        result = prime * result + if (username == null) 0 else username!!.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (!super.equals(other)) return false
        if (javaClass != other!!.javaClass) return false
        val obj = other as User?
        if (username == null) {
            if (obj!!.username != null) return false
        } else if (username != obj!!.username) return false
        return true
    }
}