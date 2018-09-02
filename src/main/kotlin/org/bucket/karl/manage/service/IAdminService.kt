package org.bucket.karl.manage.service

import org.bucket.karl.basic.service.IBaseService
import org.bucket.karl.manage.entity.Admin

interface IAdminService : IBaseService<Admin, Long> {
    fun findByUsername(username: String): Admin?

    fun saveOrUpdate(admin: Admin)

    fun saveAdminRoles(id: Long, roles: Array<Long>): Boolean?
}