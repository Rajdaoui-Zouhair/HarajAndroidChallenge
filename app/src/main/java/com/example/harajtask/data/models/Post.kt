package com.example.harajtask.data.models

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("title") val title: String,
    @SerializedName("username") val userName: String,
    @SerializedName("thumbURL") val thumbURL: String,
    @SerializedName("commentCount") val commentCount: Int,
    @SerializedName("city") val city: String,
    @SerializedName("date") val date: Long,
    @SerializedName("body") val body: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (title != other.title) return false
        if (userName != other.userName) return false
        if (thumbURL != other.thumbURL) return false
        if (commentCount != other.commentCount) return false
        if (city != other.city) return false
        if (date != other.date) return false
        if (body != other.body) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + userName.hashCode()
        result = 31 * result + thumbURL.hashCode()
        result = 31 * result + commentCount
        result = 31 * result + city.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + body.hashCode()
        return result
    }


}