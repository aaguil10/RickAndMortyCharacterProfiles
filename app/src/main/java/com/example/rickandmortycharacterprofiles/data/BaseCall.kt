package com.example.rickandmortycharacterprofiles.data

import android.util.Log
import com.example.rickandmortycharacterprofiles.utils.Resource
import retrofit2.Response

abstract class BaseCall {
    val TAG =  "BaseCall"

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(msg: String): Resource<T> {
        Log.d(TAG, msg)
        return Resource.error("Network call failed: $msg")
    }

}