package ru.burtsev.push_keeper.data.db.app

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApp(appEntity: AppEntity): Long


    @Query("Select * from Application ORDER BY id DESC")
    suspend fun getApps(): List<AppEntity>

    @Query("Update Application SET isEnabled = :isEnable WHERE id = :id")
    suspend fun updateIsEnableFlag(id: Long, isEnable: Boolean)

}
