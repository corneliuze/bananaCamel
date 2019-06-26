package com.example.bananacamelapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bananacamelapp.data.UiUtils
import com.example.bananacamelapp.model.BananaModel
import com.example.bananacamelapp.model.BananaViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding : com.example.bananacamelapp.databinding.ActivityMainBinding
    var  banana : Int =0
    var  camel : Int =0
    var  distance : Int =0
    var  bananaPerKm : Int =0
    private lateinit var  bananaViewModel : BananaViewModel
    private lateinit var bananaModel : BananaModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bananaViewModel = ViewModelProviders.of(this).get(BananaViewModel::class.java)
        binding.calculateButton.setOnClickListener { it
            if (checkEmpty()) return@setOnClickListener
          if (errorCheck(it))return@setOnClickListener
        calculate()
        }

    }



    fun errorCheck(view: View) : Boolean{

        binding.apply {
            banana =  numberOfBananaEt.text.toString().toInt()
            camel = numberOfCamelEt.text.toString().toInt()
            distance = distanceEt.text.toString().toInt()
            bananaPerKm = numberOfBananaPerKmEt.text.toString().toInt()
        }

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

        if (banana < 3000) {
            numberOfBanana_et.setError("Bananas must be 3000 or more")
            return true
        }
        else if (camel < 1 || camel> 10  ) {
            numberOfCamel_et.setError("Number of camel should be between 1 and 10")
            return true
        }
        else if (bananaPerKm < 1 || bananaPerKm > 10  ){
            numberOfCamel_et.setError("Number of Bananas eaten per km should be between 1 and 10")
            return true
        }
        else if (distance <1000 || distance > 10000) {
            distance_et.setError("Distance is between 1000 and 10000")
            return true
        }
        return false
    }


    fun calculate()  {

            bananaModel = BananaModel(banana,  distance, bananaPerKm, camel)
            bananaViewModel.postBananaModel(bananaModel)
            bananaViewModel.bananaData.observe(this, Observer<BananaModel> {
                bananaViewModel.getAnswer(bananaModel)
            })
            bananaViewModel.answerData.observe(this, Observer<Int>{
                var word = "oops! not enough bananas"
                if (it < 0) {
                    answer_tv.setText(word)
                }else{
                    var answer  = "$it Bananas got to the market"
                    answer_tv.text = answer
                }


            })


    }


   fun checkEmpty() : Boolean{
       var message = "This field is empty"
       if (isEmpty(numberOfBananaPerKm_et)){
           numberOfBananaPerKm_et.setError(message)
           return true
       }
       else if (isEmpty(numberOfBanana_et)){
           numberOfBanana_et.setError(message)
          return true
       }
       else if (isEmpty(numberOfCamel_et)){
           numberOfCamel_et.setError(message)
         return true
       }
       else if(isEmpty(distance_et)){
           distance_et.setError(message)
           return true
       }
       return false
   }

    fun isEmpty(editText: EditText) : Boolean{
      return  if (editText.text.toString().isEmpty()) true else return false
    }

}
