package com.calmox.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NotesModel (
    @PrimaryKey val id : String,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "data") val data : String
)