package com.datly.imagechallenge.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.lang.StringBuilder

data class Image(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("link")
    val url_link: String): Serializable, Cloneable {

    override fun toString(): String {
        var sb = StringBuilder()
        appendString(sb, "Image [")
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

data class Images (
    @SerializedName("data")
    val images: List<Image>
)
