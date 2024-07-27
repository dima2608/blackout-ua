package com.august.ua.blackout.data.local.db

import android.content.Context
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import com.august.ua.blackout.R
import com.august.ua.blackout.data.local.db.dbo.with_embeded.OutrageFullDbo
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream

class AppDatabaseCallBack(
    private val appDatabase: AppDatabase,
    private val context: Context,
    private val scope: CoroutineScope
): Callback() {

    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
        // If you want to keep the data through app restarts,
        // comment out the following line.
        appDatabase.let { database ->
            scope.launch(Dispatchers.IO) {
                val jsonObj = JsonParser().parse(
                    readJSONFromAsset(
                        context
                    )
                ).asJsonObject

                val outrage = object : TypeToken<OutrageFullDbo>() {}.type
                val outrageFull: OutrageFullDbo = Gson().fromJson(jsonObj, outrage)

                populateDatabase(
                    database,
                    outrageFull
                )
            }
        }
    }

    // Populate the database from company.json file - needs to be in a new coroutine
    // If needed, you can parse the fields you want from the database and use them
    // This example shows a list by the Developer object/class

    fun populateDatabase(database: AppDatabase, outrageFull: OutrageFullDbo) {
        val outrageDao = database.outrageDao()

        // Empty database on first load
        //outrageDao.deleteAll()

        val outrageList = outrageFull.outrages
//            outrageList?.forEach {
//                outrageDao.insert(
//
//                )
//            }
    }

    fun readJSONFromAsset(context: Context): String {
        val json: String
        try {
            val inputStream: InputStream = context.assets.open(
                context.getString(
                    R.string.outrageRes
                )
            )
            json = inputStream.bufferedReader().use {
                it.readText()
            }
        } catch (ex: Exception) {
            ex.localizedMessage
            return ""
        }
        return json
    }
}