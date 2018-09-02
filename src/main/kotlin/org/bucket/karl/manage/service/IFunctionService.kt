package org.bucket.karl.manage.service

import org.bucket.karl.basic.entity.vo.TreeNode
import org.bucket.karl.basic.service.IBaseService
import org.bucket.karl.manage.entity.Function

interface IFunctionService : IBaseService<Function, Long> {
    fun maxFunctionLevel(type: Int?, parentId: Long?): Int?

    fun loadTree(): List<TreeNode>

    fun loadFunctionByAdminId(adminId: Long?): Map<String, Set<Function>>
}