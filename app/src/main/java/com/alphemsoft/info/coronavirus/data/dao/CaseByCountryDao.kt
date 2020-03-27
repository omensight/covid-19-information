package com.alphemsoft.info.coronavirus.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.alphemsoft.info.coronavirus.data.model.CaseByCountry
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CaseByCountryDao: BaseDao<CaseByCountry> {

    @Query("SELECT * FROM case_by_country")
    abstract fun getAllCasesByCountry(): Flow<List<CaseByCountry>>
}