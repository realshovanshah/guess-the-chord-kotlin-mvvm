package com.example.guessthechord.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel(){

    init {
        Log.d(TAG, "GameViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: GameViewModel Cleared")
    }

    companion object{
        private const val TAG = "GameViewModel"
    }
}