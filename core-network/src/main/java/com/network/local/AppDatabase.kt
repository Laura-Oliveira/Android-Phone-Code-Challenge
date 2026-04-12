package com.network.local

import data.model.SongDao
import androidx.room.Database
import androidx.room.RoomDatabase
import data.model.SongEntity

@Database(
    entities = [SongEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun songDao(): SongDao
}