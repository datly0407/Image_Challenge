package com.datly.imagechallenge.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.lang.StringBuilder

/**
 * POJO class uses to store data when loaded from server
 * @author: Dat Ly
 * Date: 10/04/2019
 */
data class Image(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("cover")
    val cover: String,
    @SerializedName("link")
    val url_link: String): Serializable, Cloneable {

    override fun toString(): String {
        var sb = StringBuilder()
        appendString(sb, "Image [")
        appendString(sb, "ID = $id" )
        appendString(sb, "TITLE = $title")
        appendString(sb, "COVER = $cover")
        appendString(sb, "URL_LINK = $url_link")
        appendString(sb, "]")

        return sb.toString()
    }

    private fun appendString(builder: StringBuilder, data: String) {
        builder.append(data + System.lineSeparator())
    }
}

data class ImageList (
    @SerializedName("data")
    val imageList: List<Image>
)
