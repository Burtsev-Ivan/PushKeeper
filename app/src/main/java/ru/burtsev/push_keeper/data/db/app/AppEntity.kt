package ru.burtsev.push_keeper.data.db.app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Application")
data class AppEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val packages: String,
    val appName: String,
    val isEnabled: Boolean,
)
