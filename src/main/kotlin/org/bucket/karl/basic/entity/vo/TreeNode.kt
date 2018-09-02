package org.bucket.karl.basic.entity.vo

import java.io.Serializable

class TreeNode : Serializable {
    var id: Long? = null

    var name: String? = null

    var pid: Long? = null

    var type: Int = 0

    var status = 1

//    var iconSkin = ""

    var isParent = true

    var isChecked = false

    var isOpen = false
}