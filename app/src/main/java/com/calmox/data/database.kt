package com.calmox.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.calmox.Model.NotesModel

@Database(entities = [NotesModel::class], version = 1, exportSchema = false)
abstract class database : RoomDatabase() {
    abstract fun dao() : dao

    companion object{
        @Volatile
        private var INSTANCE : database? = null
        fun getDatabase(context: Context):database
        {
            val tempInstance = INSTANCE

            if(tempInstance!=null)
            {
                return tempInstance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(context.applicationContext,
                    database::class.java,
                    "notes_database").build()

                INSTANCE = instance
                return instance
            }
        }
    }
}