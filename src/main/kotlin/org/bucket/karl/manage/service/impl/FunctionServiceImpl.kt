package org.bucket.karl.manage.service.impl

import org.bucket.karl.basic.dao.IBaseDAO
import org.bucket.karl.basic.entity.vo.TreeNode
import org.bucket.karl.basic.service.impl.BaseServiceImpl
import org.bucket.karl.manage.dao.IFunctionDAO
import org.bucket.karl.manage.entity.Function
import org.bucket.karl.manage.service.IFunctionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FunctionServiceImpl : BaseServiceImpl<Function, Long>(), IFunctionService {
    @Autowired
    private lateinit var functionDAO: IFunctionDAO

    override val baseDao: IBaseDAO<Function, Long>
        get() = this.functionDAO

    override fun maxFunctionLevel(type: Int?, parentId: Long?): Int? {
        if (parentId == null || parentId <= 0) {
            return functionDAO.maxFunctionLevel(type!!)
        }
        return functionDAO.maxFunctionLevel(parentId)
    }

    override fun save(entity: Function) {
        var e = entity
        if (e.id == null) {
            var level: Int? = 0
            if (e.parent != null && e.parent!!.id != null) {
                level = functionDAO.maxFunctionLevel(e.parent!!.id!!)
                e.parent = find(e.parent!!.id!!)
            } else {
                level = functionDAO.maxFunctionLevel(e.type!!)
                e.parent = null
            }
            level = (level ?: 0) + 1
            e.level = level
            e.active = true
        } else {
            val entityFunction = find(e.id!!)
            entityFunction.name = e.name
            entityFunction.url = e.url
            entityFunction.shiroPermission = e.shiroPermission
            e = entityFunction
        }
        super.save(e)
    }

    override fun loadTree(): List<TreeNode> {
        val fArr = findAll()
        val tnArr = ArrayList<TreeNode>(fArr.size)
        fArr.forEach {
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
            tnArr.add(node)
        }
        return tnArr
    }

    override fun loadFunctionByAdminId(adminId: Long?): Map<String, Set<Function>> {
        val funs = functionDAO.loadFunctionByAdminId(adminId)
        val mapfuns = hashMapOf<String, Set<Function>>()
        funs.forEach {
            var id = "0"
            if (it.parent != null) {
                id = it.parent!!.id.toString()
            }
            if (mapfuns[id] != null) {
                val funset = mapfuns[id] as HashSet<Function>
                funset.add(it)
            } else {
                val tops = hashSetOf(it)
                mapfuns[id] = tops
            }
        }
        return mapfuns
    }
}