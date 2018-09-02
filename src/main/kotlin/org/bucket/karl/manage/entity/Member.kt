package org.bucket.karl.manage.entity

import org.bucket.karl.basic.entity.BaseEntity
import java.util.*
import javax.persistence.Entity

@Entity(name = "sys_member")
class Member : BaseEntity() {
    var name: String? = null
    var sex: Int? = null
    var birthday: Date? = null
    var avatar: String? = null

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}