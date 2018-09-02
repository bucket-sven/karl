package org.bucket.karl.manage.service.impl

import com.alibaba.fastjson.JSON
import org.bucket.karl.basic.dao.IBaseDAO
import org.bucket.karl.basic.entity.vo.TreeNode
import org.bucket.karl.basic.service.impl.BaseServiceImpl
import org.bucket.karl.manage.dao.IRoleDAO
import org.bucket.karl.manage.entity.Function
import org.bucket.karl.manage.entity.Role
import org.bucket.karl.manage.service.IFunctionService
import org.bucket.karl.manage.service.IRoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl : BaseServiceImpl<Role, Long>(), IRoleService {
    @Autowired
    private lateinit var roleDAO: IRoleDAO
    @Autowired
    private lateinit var functionService: IFunctionService

    override val baseDao: IBaseDAO<Role, Long>
        get() = this.roleDAO

    override fun saveOrUpdate(role: Role) {
        println("QIAONIMA-------")
        println(JSON.toJSONString(role))
        if (role.id != null) {
            val roleOne = find(role.id!!)
            roleOne.name = role.name
            update(roleOne)
        } else save(role)
    }

    override fun loadTreeAndMarkRoleFunctions(roleId: Long): List<TreeNode> {
        val role = find(roleId)
        val roleFunctionSet = role.functions

        val functionArray = functionService.findAll()
        val tnArray = ArrayList<TreeNode>(functionArray.size)
        functionArray.forEach {
            val node = TreeNode()
            node.id = it.id
            if (it.parent == null) {
                node.pid = -1L
            } else {
                node.pid = it.parent!!.id
            }
            node.name = it.name
            if (it.child != null) {
                node.isParent = true
            }
            if (roleFunctionSet != null) {
                if (roleFunctionSet.contains(it)) {
                    node.isChecked = true
                }
            }
            tnArray.add(node)
        }
        return tnArray
    }

    override fun saveRoleFunctions(roleId: Long, functions: Set<Function>?): Boolean? {
        try {
            val role = find(roleId)
            if (functions != null) {
                val newFunctions = HashSet<Function>(functions.size)
                functions.forEach { newFunctions.add(functionService.find(it.id!!)) }
                role.functions = newFunctions
            }
            super.save(role)
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }
}