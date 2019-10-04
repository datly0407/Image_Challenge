package com.datly.imagechallenge.data.model

import java.lang.StringBuilder

data class ItemModel(
    val id: String,
    val title: String,
    val description: String,
    val url_link: String) {

    override fun toString(): String {
        var sb = StringBuilder()
        appendString(sb, "Image Item [")
        appendString(sb, "ID = $id" )
        appendString(sb, "TITLE = $title")
        appendString(sb, "DESCRIPTION = $description")
        appendString(sb, "URL_LINK = $url_link")
        appendString(sb, "]")

        return sb.toString()
    }

    private fun appendString(builder: StringBuilder, data: String) {
        builder.append(data + System.lineSeparator())
    }
}