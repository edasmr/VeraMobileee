package com.example.veramobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    private lateinit var image:ImageView
    private lateinit var platform:TextView
    private lateinit var sn:TextView
    private lateinit var macAddress:TextView
    private lateinit var firmware:TextView
    private lateinit var model:TextView
    private lateinit var item:DeviceModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        image = findViewById(R.id.listImage2)
        platform = findViewById(R.id.platform2)
        sn = findViewById(R.id.sn)
        macAddress = findViewById(R.id.macAddress)
        firmware = findViewById(R.id.firmware)
        model = findViewById(R.id.model)
        
        item = intent.getParcelableExtra("data")!!
        if(item.platform =="Sercomm G450") {
            Glide.with(this).load(R.drawable.vera_plus_big).into(image)
        }else if(item.platform =="Sercomm G550"){
            Glide.with(this).load(R.drawable.vera_secure_big).into(image)
        }else if(item.platform =="MiCasaVerde VeraLite"){
            Glide.with(this).load(R.drawable.vera_edge_big).into(image)
        }else if(item.platform =="Sercomm NA930"){
            Glide.with(this).load(R.drawable.vera_edge_big).into(image)
        }else if(item.platform =="Sercomm NA900"){
            Glide.with(this).load(R.drawable.vera_edge_big).into(image)
        }else if(item.platform =="Sercomm NA301"){
            Glide.with(this).load(R.drawable.vera_edge_big).into(image)
        }else {
            Glide.with(this).load(R.drawable.vera_edge_big).into(image)

        }
        platform.text = item.platform
        sn.text = item.pkDevice.toString()
        macAddress.text = item.macAddress
        firmware.text = item.firmware
        model.text = item.platform


    }


}

