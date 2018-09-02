package org.bucket.karl.manage.dao

import org.bucket.karl.basic.dao.IBaseDAO
import org.bucket.karl.manage.entity.Role
import org.springframework.stereotype.Repository

@Repository
interface IRoleDAO : IBaseDAO<Role, Long>
