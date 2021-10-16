package com.example.rickandmortycharacterprofiles.characters


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortycharacterprofiles.data.CharacterEntity
import com.example.rickandmortycharacterprofiles.data.CharacterRepo
import com.example.rickandmortycharacterprofiles.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repo: CharacterRepo
) : ViewModel() {

    val characters = repo.getCharacters()
}