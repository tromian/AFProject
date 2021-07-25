package com.tromian.game.afproject.data.db.entityes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "legal_age")
    val pgAge: Int? = null,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "genres")
    val genres: String? = null,
    @ColumnInfo(name = "running_time")
    val runningTime: Int? = null,
    @ColumnInfo(name = "review_count")
    val reviewCount: Int? = null,
    @ColumnInfo(name = "rating")
    val rating: Int? = null,
    @ColumnInfo(name = "image_url")
    val imageUrl: String? = null,
    @ColumnInfo(name = "image_url_detail")
    val detailImageUrl: String? = null,
    @ColumnInfo(name = "story")
    val storyLine: String,
    @ColumnInfo(name = "liked")
    val isLiked: Boolean = false,
)
