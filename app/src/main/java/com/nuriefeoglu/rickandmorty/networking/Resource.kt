package com.nuriefeoglu.rickandmorty.networking

enum class Status {
    SUCCESS,
    FAIL,
    LOADING
}

data class Resource<T>(
    val status: Status,
    val data: T? = null,
    val errorMessage: String? = null
) {

    companion object {

        @JvmStatic
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        @JvmStatic
        fun <T> fail(message: String?): Resource<T> {
            return Resource(Status.FAIL, errorMessage = message)
        }

        fun <T> loading(): Resource<T> = Resource(Status.LOADING)

    }


}