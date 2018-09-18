package org.bucket.karl.basic.entity

import org.bucket.karl.basic.listener.BaseEntityListener
import org.hibernate.validator.constraints.Length
import java.io.Serializable
import java.util.*
import javax.persistence.*

@MappedSuperclass
@EntityListeners(BaseEntityListener::class)
abstract class BaseEntity : Serializable {
    @Length(min = 1, max = 1)
    var delFlag = DEL_FLAG_NORMAL

    @Id
    @GeneratedValue
    var id: Long? = null

    @ManyToOne //
    var createdBy: User? = null

//    @Temporal(TemporalType.TIMESTAMP) //
    @Column
    var createdDate: Date? = null

    @ManyToOne //
    var lastModifiedBy: User? = null

//    @Temporal(TemporalType.TIMESTAMP) //
    @Column
    var lastModifiedDate: Date? = null

    companion object {
        const val FIELD_DEL_FLAG = "delFlag"
        const val DEL_FLAG_NORMAL = "0"
        const val DEL_FLAG_DELETE = "1"
        const val DEL_FLAG_AUDIT = "2"
        const val serialVersionUID = -67188388306700736L
    }
}