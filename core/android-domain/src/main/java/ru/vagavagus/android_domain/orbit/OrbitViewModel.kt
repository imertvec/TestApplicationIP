package ru.vagavagus.android_domain.orbit

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

abstract class OrbitViewModel<STATE: Any, EVENT: Any, SIDE_EFFECT: Any>(
    initialState: STATE
) : ViewModel(), ContainerHost<STATE, SIDE_EFFECT> {
    val uiState get() = container.stateFlow
    val sideEffects get() = container.sideEffectFlow

    override val container: Container<STATE, SIDE_EFFECT> = container(initialState)
    abstract fun handleEvent(event: EVENT)
}