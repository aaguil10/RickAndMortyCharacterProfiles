package com.example.rickandmortycharacterprofiles.characters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.rickandmortycharacterprofiles.data.CharacterRepo

class CharactersViewModel @ViewModelInject constructor(
    private val repo: CharacterRepo
) : ViewModel() {

    val characters = repo.getCharacters()
}