package org.bucket.karl.configuration.freemarker.editor

import org.apache.commons.lang3.time.DateUtils
import java.beans.PropertyEditorSupport
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateEditor(private var emptyAsNull: Boolean = false, var dateFormat: String? = "yyyy-MM-dd HH:mm:ss") : PropertyEditorSupport(emptyAsNull) {
    override fun getAsText(): String {
        val date = value as Date?
        return if (date != null) SimpleDateFormat(this.dateFormat).format(date) else ""
    }

    override fun setAsText(text: String?) {
        if (text == null) {
            return
        }
        val str = text.trim { it <= ' ' }
        if (this.emptyAsNull && "" == str) return

        try {
            value = DateUtils.parseDate(str, *DATE_PATTERNS)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    companion object {
        val DATE_PATTERNS = arrayOf("yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss")
    }
}