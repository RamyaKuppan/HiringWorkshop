package com.nathansdev.roomyournotes.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CommentDao {
    @Query("SELECT * from comments")
    fun getAll(): List<Comment>

    @Insert
    suspend fun insert(note: Comment)
}