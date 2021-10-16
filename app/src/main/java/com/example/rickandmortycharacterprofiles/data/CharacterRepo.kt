package com.example.rickandmortycharacterprofiles.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.rickandmortycharacterprofiles.data.CharacterDao
import com.example.rickandmortycharacterprofiles.data.CharacterCall
import com.example.rickandmortycharacterprofiles.utils.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CharacterRepo @Inject constructor(
    private val networkSource: CharacterCall,
    private val dbSource: CharacterDao
) {

    fun getCharacter(id: Int) = runGetOp(
        dbQuery = { dbSource.getCharacter(id) },
        networkCall = { networkSource.getCharacter(id) },
        saveCallResult = { dbSource.insert(it) }
    )

    fun getCharacters() = runGetOp(
        dbQuery = { dbSource.getAllCharacters() },
        networkCall = { networkSource.getCharacters() },
        saveCallResult = { dbSource.insertAll(it.results) }
    )

    fun <T, A> runGetOp(dbQuery: () -> LiveData<T>,
                                   networkCall: suspend () -> Resource<A>,
                                   saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val source = dbQuery.invoke().map { Resource.success(it) }
            emitSource(source)

            val responseStatus = networkCall.invoke()
            if (responseStatus.status == Resource.Status.SUCCESS) {
                saveCallResult(responseStatus.data!!)

            } else if (responseStatus.status == Resource.Status.ERROR) {
                emit(Resource.error(responseStatus.message!!))
                emitSource(source)
            }
        }
}