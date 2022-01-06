package com.calmox.Model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.calmox.data.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    var readalldata = database.getDatabase(application).dao().readalldata()

    fun insert(data : NotesModel){
        viewModelScope.launch(Dispatchers.IO) {
            database.getDatabase(getApplication()).dao().insert(data)
        }
    }

    fun delete(data : NotesModel){
        viewModelScope.launch(Dispatchers.IO) {
            database.getDatabase(getApplication()).dao().delete(data)
        }
    }

    fun deleteall(){
        viewModelScope.launch(Dispatchers.IO) {
            database.getDatabase(getApplication()).dao().deleteall()
        }
    }

    fun update(data: NotesModel){
        viewModelScope.launch(Dispatchers.IO) {
            database.getDatabase(getApplication()).dao().update(data)
        }
    }

}