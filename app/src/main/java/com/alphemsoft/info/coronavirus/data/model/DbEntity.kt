package com.alphemsoft.info.coronavirus.data.model

abstract class DbEntity<T>{
    abstract var id: T
    abstract override fun equals(other: Any?): Boolean
    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}