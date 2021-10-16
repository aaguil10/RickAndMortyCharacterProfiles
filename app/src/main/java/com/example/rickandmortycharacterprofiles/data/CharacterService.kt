package com.example.rickandmortycharacterprofiles.data

import com.example.rickandmortycharacterprofiles.data.CharacterListEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {
    @GET("character")
    suspend fun getAllCharacters() : Response<CharacterListEntity>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<CharacterEntity>
}