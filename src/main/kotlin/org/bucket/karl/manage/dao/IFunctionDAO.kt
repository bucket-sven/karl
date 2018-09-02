package org.bucket.karl.manage.dao

import org.bucket.karl.basic.dao.IBaseDAO
import org.bucket.karl.manage.entity.Function
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface IFunctionDAO : IBaseDAO<Function, Long> {
    @Query(nativeQuery = true, value = "SELECT level FROM sys_function WHERE type = ?1 ORDER BY level DESC LIMIT 1")
    fun maxFunctionLevel(type: Int): Int

    @Query(nativeQuery = true, value = "SELECT level FROM sys_function sf WHERE sf.parent_id = ?1 ORDER BY level DESC LIMIT 1")
    fun maxFunctionLevel(parentId: Long): Int?

    @Query(nativeQuery = true, value = "SELECT sf.* FROM sys_admin_role sar, sys_role sr, sys_role_function srf, sys_function sf WHERE sar.role_id = sr.id AND sr.id = srf.role_id AND srf.function_id = sf.id AND sar.admin_id = :adminId  order by type asc")
    fun loadFunctionByAdminId(@Param("adminId") adminId: Long?): Set<Function>
}