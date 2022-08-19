package com.ork.maptravel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ork.maptravel.model.Place
import com.ork.maptravel.roomdatabase.PlaceDao

@Database(entities = [Place::class], version = 1)
abstract class PlaceDatabase:RoomDatabase() {

    abstract fun placeDao():PlaceDao

    companion object{

        @Volatile private var instance:PlaceDatabase? = null
        private val lock = Any()

        operator fun invoke(context: Context) = instance?:  synchronized(lock){
            instance?: makeDatabase(context).also {
                instance  = it
            }
        }


        private fun  makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, PlaceDatabase::class.java, ""
        ).build()
    }




}