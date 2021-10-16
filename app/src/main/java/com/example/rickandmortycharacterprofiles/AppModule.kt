package com.example.rickandmortycharacterprofiles

import android.content.Context
import com.example.rickandmortycharacterprofiles.data.AppDb
import com.example.rickandmortycharacterprofiles.data.CharacterDao
import com.example.rickandmortycharacterprofiles.data.CharacterCall
import com.example.rickandmortycharacterprofiles.data.CharacterService
import com.example.rickandmortycharacterprofiles.data.CharacterRepo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService = retrofit.create(CharacterService::class.java)

    @Singleton
    @Provides
    fun provideCharacterCall(characterService: CharacterService) = CharacterCall(characterService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDb.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDb) = db.characterDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: CharacterCall,
                          localDataSource: CharacterDao) =
        CharacterRepo(remoteDataSource, localDataSource)
}