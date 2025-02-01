package com.example.core.util

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    val isConnected : Flow<Boolean>
}