package com.example.core.util


sealed interface Result <T>{
    data class Success<T>( val data : T ) : Result<T>
    data class Error<T>(val error : Exception ,val message : String?) : Result<T>

    fun <R> transform(
        transform : (T) -> R
    ) : Result<R>{
        return when(this){
            is Success -> Success(transform(data))
            is Error -> Error(error,message)
        }
    }

}