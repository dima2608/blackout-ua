package com.august.ua.blackout.data.local.db.dbo.with_embeded

import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//@Entity(tableName = "outrages_table")
//data class OutragesDbo(
//    @PrimaryKey(autoGenerate = true)
//    @SerializedName("id")
//    @Expose
//    var outrageId: Int = 0,
//    @SerializedName("outrages")
//    @Expose
//    @Embedded(prefix = "outrages_")
//    var outrages: List<OutrageDbo>,
//){
//    constructor() : this(0, emptyList())
//}