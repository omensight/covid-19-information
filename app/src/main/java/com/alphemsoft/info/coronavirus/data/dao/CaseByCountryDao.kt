package com.alphemsoft.info.coronavirus.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.alphemsoft.info.coronavirus.data.model.CaseByCountry
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CaseByCountryDao: BaseDao<CaseByCountry> {

    @Query("SELECT DISTINCT * FROM case_by_country WHERE case_by_country_showable = 1 GROUP BY case_by_country_country_name ")
    abstract fun getAllCasesByCountry(): Flow<List<CaseByCountry>>

    @Query("""
        SELECT * FROM case_by_country 
        WHERE case_by_country_country_name = :countryName
        AND case_by_country_showable = 0""")
    abstract fun findCountryHistory(countryName: String): Flow<List<CaseByCountry>>
}