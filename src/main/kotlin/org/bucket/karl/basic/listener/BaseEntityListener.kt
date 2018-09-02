package org.bucket.karl.basic.listener

import org.bucket.karl.basic.entity.BaseEntity
import org.bucket.karl.basic.utils.UserUtils
import org.springframework.stereotype.Component
import java.util.*
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

@Component
class BaseEntityListener {
    @PrePersist
    fun initEntity(baseEntity: BaseEntity) {
        val time = Date()
        baseEntity.createdDate = time
        baseEntity.lastModifiedDate = time
        val currentUser = UserUtils.currentUser
        if (currentUser != null) {
            baseEntity.createdBy = currentUser
            baseEntity.lastModifiedBy = currentUser
        }
    }


    @PreUpdate
    fun updateEntity(baseEntity: BaseEntity) {
        val time = Date()
        baseEntity.lastModifiedDate = time
        val currentUser = UserUtils.currentUser
        if (currentUser != null) {
            baseEntity.lastModifiedBy = currentUser
        }
    }
}