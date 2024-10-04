package ru.vagavagus.android_domain

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

abstract class OrbitViewModel<STATE: Any, EVENT: Any, SIDE_EFFECT: Any>(
    initialState: STATE
) : ViewModel(), ContainerHost<STATE, SIDE_EFFECT> {

    override val container: Container<STATE, SIDE_EFFECT> = container(initialState)
    abstract fun handleEvent(event: EVENT)
}