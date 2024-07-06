package com.august.ua.blackout.data.local.db.dbo.converters

import androidx.room.TypeConverter
import com.august.ua.blackout.data.local.db.dbo.with_embeded.OutrageDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.OutrageQueueDbo
import com.august.ua.blackout.data.local.db.dbo.with_embeded.OutrageShiftDbo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromOutragesList(value: List<OutrageDbo>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toOutragesList(value: String?): List<OutrageDbo>? {
        val listType = object : TypeToken<List<OutrageDbo>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromOutrageQueueList(value: List<OutrageQueueDbo>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toOutrageQueueList(value: String?): List<OutrageQueueDbo>? {
        val listType = object : TypeToken<List<OutrageQueueDbo>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromOutrageShiftList(value: List<OutrageShiftDbo>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toOutrageShiftList(value: String?): List<OutrageShiftDbo>? {
        val listType = object : TypeToken<List<OutrageShiftDbo>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

//    @TypeConverter
//    fun stringListToJson(value: List<String>?) = Gson().toJson(value)
//
//    @TypeConverter
//    fun jsonToStringList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}