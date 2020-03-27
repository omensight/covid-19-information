package com.alphemsoft.info.coronavirus.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.alphemsoft.info.coronavirus.data.model.GlobalSettings
import kotlinx.coroutines.flow.Flow

@Dao
abstract class GlobalSettingsDao: BaseDao<GlobalSettings> {

    @Query("SELECT * FROM global_settings WHERE global_settings_id = :uniqueId")
    abstract fun getGlobalSettingsFlow(uniqueId: String = GlobalSettings.UNIQUE_ID): Flow<GlobalSettings>

    @Query("SELECT * FROM global_settings WHERE global_settings_id = :uniqueId")
    abstract suspend fun getGlobalSettings(uniqueId: String = GlobalSettings.UNIQUE_ID): GlobalSettings?
}