package com.alphemsoft.info.coronavirus.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg item:T)

    @Update
    fun update(vararg item: T)

    @Delete
    fun delete(vararg item: T)
}