package com.nathansdev.roomyournotes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Comment::class], version = 1)
abstract class VideoNowDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: VideoNowDatabase? = null

        fun getDatabase(context: Context): VideoNowDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        VideoNowDatabase::class.java,
                        "Word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}