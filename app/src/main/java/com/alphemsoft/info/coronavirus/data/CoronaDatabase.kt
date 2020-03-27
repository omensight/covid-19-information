package com.alphemsoft.info.coronavirus.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alphemsoft.info.coronavirus.data.dao.CaseByCountryDao
import com.alphemsoft.info.coronavirus.data.dao.GlobalSettingsDao
import com.alphemsoft.info.coronavirus.data.model.CaseByCountry
import com.alphemsoft.info.coronavirus.data.model.GlobalSettings

@Database(version = 1, entities = [CaseByCountry::class, GlobalSettings::class])
@TypeConverters(com.alphemsoft.info.coronavirus.data.TypeConverters::class)
abstract class CoronaDatabase: RoomDatabase() {
    abstract fun casesByCountryDao(): CaseByCountryDao
    abstract fun globalSettingsDao(): GlobalSettingsDao
}