package com.calmox.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.calmox.Model.NotesModel

@Dao
interface dao {
    @Insert
    fun insert(data : NotesModel)

    @Delete
    fun delete(data : NotesModel)

    @Query("delete from notes")
    fun deleteall()

    @Query("select * from notes order by id asc")
    fun readalldata() : LiveData<List<NotesModel>>

    @Update
    fun update(data : NotesModel)

}