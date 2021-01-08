package com.po4yka.geoquiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CheatActivityViewModel : ViewModel() {

    private var mutableRightAnswer: MutableLiveData<Boolean> = MutableLiveData()

    var rightAnswer: Boolean
        get() {
            isAnswerShown = true
            return mutableRightAnswer.value!!
        }
        set(value) {
            mutableRightAnswer.value = value
        }

    private var mutableIsAnswerShown: MutableLiveData<Boolean> = MutableLiveData()

    var isAnswerShown: Boolean
        get() = mutableIsAnswerShown.value!!
        set(value) {
            mutableIsAnswerShown.value = value
        }

    init {
        mutableIsAnswerShown.value = false
    }

    fun getAnswer(): Int {
        return if (rightAnswer) R.string.true_button else R.string.false_button
    }
}