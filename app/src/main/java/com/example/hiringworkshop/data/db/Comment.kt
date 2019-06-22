package com.nathansdev.roomyournotes.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
class Comment(@PrimaryKey @ColumnInfo(name = "comment") val title: String) {

}