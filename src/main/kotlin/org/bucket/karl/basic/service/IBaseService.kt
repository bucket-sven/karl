package org.bucket.karl.basic.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import java.io.Serializable

interface IBaseService<T, ID : Serializable> {
    fun find(id: ID): T

    fun findAll(): List<T>

    fun findList(ids: Array<ID>): List<T>

    fun findList(ids: Iterable<ID>): List<T>

    fun findAll(pageable: Pageable): Page<T>

    fun findAll(spec: Specification<T>, pageable: Pageable): Page<T>

    fun count(): Long

    fun count(spec: Specification<T>): Long

    fun exists(id: ID): Boolean

    fun save(entity: T)

    fun update(entity: T): T

    fun delete(id: ID)

    fun deleteByIds(vararg ids: ID)

    fun delete(entities: Array<T>)

    fun delete(entities: Iterable<T>)

    fun delete(entity: T)

    fun findList(spec: Specification<T>, sort: Sort): List<T>
}
