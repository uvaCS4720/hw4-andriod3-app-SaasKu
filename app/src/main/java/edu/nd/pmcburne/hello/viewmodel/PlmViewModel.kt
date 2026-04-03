package edu.nd.pmcburne.hello.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import edu.nd.pmcburne.hello.model.Placemark
import edu.nd.pmcburne.hello.model.PlmDao
import edu.nd.pmcburne.hello.model.PlmDatabase
import edu.nd.pmcburne.hello.model.PlmRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.launch

class PlmViewModel(application: Application): AndroidViewModel(application) {
    private val dao: PlmDao = PlmDatabase.getDatabase(application).plmDao()

    private val repo: PlmRepo = PlmRepo(dao)

    private val tagsList: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())

    val tags: StateFlow<List<String>> = tagsList.asStateFlow()

    private val selectedTag: MutableStateFlow<String> = MutableStateFlow("core")

    val st: StateFlow<String> = selectedTag.asStateFlow()

    private val placemarkList: MutableStateFlow<List<Placemark>> = MutableStateFlow(emptyList())

    val plms: StateFlow<List<Placemark>> = placemarkList.asStateFlow()

    init{
        loadPlmData()
    }

    private fun loadPlmData(){
        viewModelScope.launch{
            repo.syncPlacemarks()
            tagsList.value = repo.getAllTags()
            placemarkList.value = repo.getPlmsForTag(selectedTag.value)

        }
    }

    fun setTag(tag: String){
        selectedTag.value = tag
        viewModelScope.launch{
            placemarkList.value = repo.getPlmsForTag(tag)
        }
    }
}




