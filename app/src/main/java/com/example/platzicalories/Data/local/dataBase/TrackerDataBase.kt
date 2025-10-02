package com.example.platzicalories.Data.local.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.platzicalories.Data.local.dao.TrackerDao
import com.example.platzicalories.Data.local.entity.TrackedFoodEntity

@Database(
    entities = [TrackedFoodEntity::class],
    version = 1
)
abstract class TrackerDatabase : RoomDatabase() {
    abstract val dao: TrackerDao
}