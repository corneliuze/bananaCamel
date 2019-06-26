package com.example.bananacamelapp.data

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import com.example.bananacamelapp.MainActivity
import com.example.bananacamelapp.R

class UiUtils(private val context : Context) {

    private var popupWindow : PopupWindow ?= null

    fun showLoadingSuccess(view: View, ans: String) {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutT = layoutInflater.inflate(R.layout.success, null, false)
        popupWindow = PopupWindow(layoutT,ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true
        )
        val answerText = layoutT.findViewById<TextView>(R.id.sucessT)
     if (popupWindow != null){
         popupWindow!!.showAtLocation(view, Gravity.CENTER, 0, 0)
         answerText.setText(ans)
     }
    }

    fun stopLoadingSuccessBg(){
        if (popupWindow!= null) popupWindow!!.dismiss()
    }

    fun showLoadingOopsBg(view : View) {
        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        popupWindow = PopupWindow(
            layoutInflater.inflate(R.layout.oops, null, false),
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true
        )
        if (popupWindow != null) popupWindow!!.showAtLocation(view, Gravity.CENTER, 0, 0)
    }

    fun stopLoadingOops(){
        if (popupWindow!= null) popupWindow!!.dismiss()
    }


}



