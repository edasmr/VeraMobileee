package com.example.veramobile

interface IListeler {

    fun onClick(position:Int)
    fun buttonClick(item:DeviceModel, position:Int)
    fun onLongClick(position: Int)
}