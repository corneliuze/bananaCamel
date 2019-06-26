package com.example.bananacamelapp.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BananaViewModel() : ViewModel(){
    val bananaData = MutableLiveData<BananaModel>()
    val answerData = MutableLiveData<Int>()

    fun postBananaModel(bananaModel: BananaModel){
        bananaData.postValue(bananaModel)
    }

    fun getAnswer(bananaModel: BananaModel){
        var bananaLogic = Banana(bananaModel.banana, bananaModel.distance, bananaModel.camelNumber, bananaModel.bananaPerKm)
        var answer = bananaLogic.getMaximumBanana()
        answerData.postValue(answer)

    }





}