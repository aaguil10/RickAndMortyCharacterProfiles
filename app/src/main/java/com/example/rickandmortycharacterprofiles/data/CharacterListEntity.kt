package com.example.rickandmortycharacterprofiles.data

data class CharacterListEntity(
    val info: Info,
    val results: List<CharacterEntity>
)