package com.example.rickandmortycharacterprofiles.data

import javax.inject.Inject


class CharacterCall @Inject constructor(
    private val characterService: CharacterService
): BaseCall() {

    suspend fun getCharacters() = getResult { characterService.getAllCharacters() }
    suspend fun getCharacter(id: Int) = getResult { characterService.getCharacter(id) }
}