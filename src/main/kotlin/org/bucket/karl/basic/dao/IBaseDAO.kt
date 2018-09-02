package org.bucket.karl.basic.dao

import org.bucket.karl.basic.entity.BaseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable

@NoRepositoryBean
interface IBaseDAO<T : BaseEntity, ID : Serializable> : JpaRepository<T, ID>, JpaSpecificationExecutor<T>