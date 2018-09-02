package org.bucket.karl.manage.service

import org.bucket.karl.basic.entity.vo.TreeNode
import org.bucket.karl.basic.service.IBaseService
import org.bucket.karl.manage.entity.Function
import org.bucket.karl.manage.entity.Role

interface IRoleService : IBaseService<Role, Long> {
    fun saveOrUpdate(role: Role)

    fun loadTreeAndMarkRoleFunctions(roleId: Long): List<TreeNode>

    fun saveRoleFunctions(roleId: Long, functions: Set<Function>?): Boolean?
}