package ru.vagavagus.android_domain.wrappers

sealed class ResourceState <out T> {
    data class Success<out T> (val data: T): ResourceState<T>()

    /*We can put anything args to constructor, what we wish handle*/
    data class Error (val errorMessage: String): ResourceState<Nothing>()
    object Loading: ResourceState<Nothing>()
    object Idle: ResourceState<Nothing>()
}