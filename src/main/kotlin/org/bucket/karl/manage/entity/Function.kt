package org.bucket.karl.manage.entity

import org.bucket.karl.basic.entity.BaseEntity
import javax.persistence.*

@Entity(name = "sys_function")
class Function : BaseEntity() {
//    companion object {
//        const val FUNCTION_TYPE_CATEGORY = 1
//        const val FUNCTION_TYPE_MENU = 2
//        const val FUNCTION_TYPE_HREF = 3
//        const val FUNCTION_TYPE_BUTTON = 4
//    }

    @Column(length = 100)
    var name: String? = null
    @Column(length = 200)
    var url: String? = null
    @Column(length = 2, nullable = false)
    var type: Int? = null
    @Column(length = 200)
    var funcs: String? = null
    @Column(length = 250)
    var description: String? = null
    @Column(length = 10)
    var level: Int? = null
    @Column(name = "is_active", nullable = false)
    var active: Boolean? = null
    @Column(length = 200)
    var shiroPermission: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: Function? = null

    @ManyToMany(mappedBy = "functions")
    var roles: Set<Role>? = null

    @OneToMany(mappedBy = "parent")
    var child: Set<Function>? = null

    val treeName: String?
        get() = name

    val treeParent: Function?
        get() = parent

//    override fun hashCode(): Int {
//        val prime = 31
//        var result = super.hashCode()
//        result = prime * result + if (id == null) 0 else id.hashCode()
//        return result
//    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (!super.equals(obj)) return false
        if (javaClass != obj!!.javaClass) return false
        val other = obj as Function?
        if (id == null) {
            if (other!!.id != null) return false
        } else if (id != other!!.id) return false
        return true
    }
}