package org.bucket.karl.manage.dao

import org.bucket.karl.basic.dao.IBaseDAO
import org.bucket.karl.manage.entity.Admin
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface IAdminDAO : IBaseDAO<Admin, Long> {
    // 这里的 表名 对应的是@Entity注解的name属性
//    @Query("FROM Admin admin WHERE admin.user.username = ?1")
    @Query("FROM Admin WHERE id = 1")
    fun findByUsername(username: String): Admin?
}