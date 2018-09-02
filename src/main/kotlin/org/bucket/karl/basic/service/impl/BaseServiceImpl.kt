package org.bucket.karl.basic.service.impl

import org.bucket.karl.basic.dao.IBaseDAO
import org.bucket.karl.basic.entity.BaseEntity
import org.bucket.karl.basic.service.IBaseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import java.io.Serializable
import java.util.*
import javax.transaction.Transactional

@Transactional
abstract class BaseServiceImpl<T: BaseEntity, ID : Serializable> : IBaseService<T, ID> {
    abstract val baseDao: IBaseDAO<T, ID>

    override fun find(id: ID): T {
        return baseDao.findById(id).get()
    }

    override fun findAll(): List<T> {
        return baseDao.findAll()
    }

    override fun findList(ids: Array<ID>): List<T> {
        val idList = Arrays.asList(*ids)
        return baseDao.findAllById(idList)
    }

    override fun findList(spec: Specification<T>, sort: Sort): List<T> {
        return baseDao.findAll(spec, sort)
    }

    override fun findAll(pageable: Pageable): Page<T> {
        return baseDao.findAll(pageable)
    }

    override fun count(): Long {
        return baseDao.count()
    }

    override fun count(spec: Specification<T>): Long {
        return baseDao.count(spec)
    }

    override fun exists(id: ID): Boolean {
        return baseDao.existsById(id)
    }

    override fun save(entity: T) {
        baseDao.save(entity)
    }

    fun save(entities: Iterable<T>) {
        baseDao.saveAll(entities)
    }

    override fun update(entity: T): T {
        return baseDao.saveAndFlush(entity)
    }

    override fun delete(id: ID) {
        baseDao.deleteById(id)
    }

    override fun deleteByIds(vararg ids: ID) {
        for (i in ids.indices) {
            val id = ids[i]
            this.delete(id)
        }
    }

    override fun delete(entitys: Array<T>) {
        val tList = Arrays.asList(*entitys)
        baseDao.deleteAll(tList)
    }

    override fun delete(entitys: Iterable<T>) {
        baseDao.deleteAll(entitys)
    }

    override fun delete(entity: T) {
        baseDao.delete(entity)
    }

    override fun findList(ids: Iterable<ID>): List<T> {
        return baseDao.findAllById(ids)
    }

    override fun findAll(spec: Specification<T>, pageable: Pageable): Page<T> {
        return baseDao.findAll(spec, pageable)
    }
}