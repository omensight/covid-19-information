package com.alphemsoft.info.coronavirus.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "case_by_country")
data class CaseByCountry(
    @SerializedName("id")
    @ColumnInfo(name = "case_by_country_id")
    @PrimaryKey
    override var id: Int,

    @SerializedName("country_name")
    @ColumnInfo(name = "case_by_country_country_name")
    var countryName: String,

    @SerializedName("cases", alternate = ["total_cases"])
    @ColumnInfo(name = "case_by_country_cases")
    val cases : Long,

    @SerializedName("deaths", alternate = ["total_deaths"])
    @ColumnInfo(name = "case_by_country_deaths")
    val deaths : Long,

    @SerializedName("region")
    @ColumnInfo(name = "case_by_country_region")
    val region : String? = null,

    @SerializedName("total_recovered")
    @ColumnInfo(name = "case_by_country_total_recovered")
    val totalRecovered : Long,

    @SerializedName("new_deaths")
    @ColumnInfo(name = "case_by_country_new_deaths")
    val newDeaths: Long,

    @SerializedName("new_cases")
    @ColumnInfo(name = "case_by_country_new_cases")
    val newCases : Long,

    @SerializedName("serious_critical")
    @ColumnInfo(name = "case_by_country_serious_critical")
    val seriousCritical : Long,

    @SerializedName("active_cases")
    @ColumnInfo(name = "case_by_country_active_cases")
    val activeCases : Long,

    @SerializedName("total_cases_per_1m_population", alternate = ["total_cases_per1m"])
    @ColumnInfo(name = "case_by_total_cases_per_1m_population")
    val casesPerOneMillionPopulation : Long,

    @SerializedName("record_date")
    @ColumnInfo(name = "case_by_country_record_date")
    var recordDate: Date?,

    @SerializedName("showable")
    @ColumnInfo(name = "case_by_country_showable")
    var showable: Boolean = false

): DbEntity<Int>(){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as CaseByCountry

        if (id != other.id) return false
        if (cases != other.cases) return false
        if (deaths != other.deaths) return false
        if (region != other.region) return false
        if (totalRecovered != other.totalRecovered) return false
        if (newDeaths != other.newDeaths) return false
        if (newCases != other.newCases) return false
        if (seriousCritical != other.seriousCritical) return false
        if (activeCases != other.activeCases) return false
        if (casesPerOneMillionPopulation != other.casesPerOneMillionPopulation) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + cases.hashCode()
        result = 31 * result + deaths.hashCode()
        result = 31 * result + region.hashCode()
        result = 31 * result + totalRecovered.hashCode()
        result = 31 * result + newDeaths.hashCode()
        result = 31 * result + newCases.hashCode()
        result = 31 * result + seriousCritical.hashCode()
        result = 31 * result + activeCases.hashCode()
        result = 31 * result + casesPerOneMillionPopulation.hashCode()
        return result
    }
    
    fun isDifferent(other: CaseByCountry): Boolean {
        if (cases != other.cases) return true
        if (deaths != other.deaths) return true
        if (region != other.region) return true
        if (totalRecovered != other.totalRecovered) return true
        if (newDeaths != other.newDeaths) return true
        if (newCases != other.newCases) return true
        if (seriousCritical != other.seriousCritical) return true
        if (activeCases != other.activeCases) return true
        if (casesPerOneMillionPopulation != other.casesPerOneMillionPopulation) return true
        return false
    }
}