package com.example.veramobile

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerview: RecyclerView
    private lateinit var listener:IListeler
    private lateinit var duzenle:Button
    private lateinit var list:Devices
    private lateinit var layoutManager:RecyclerView.LayoutManager
    private lateinit var adapter: ListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerview=findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL ,false)




        listener = object : IListeler{
            override fun onClick(position: Int) {
                val intent = Intent(this@MainActivity,DetailActivity::class.java)
                intent.putExtra("data", list.devices[position])
                startActivity(intent)
            }

            override fun buttonClick(item: DeviceModel, position:Int) {
                list.devices.removeAt(position)
                list.devices.add(position, item)
                adapter.dataChanged(list.devices)
            }

            override fun onLongClick(position: Int) {

                val dialogClickListener =
                    DialogInterface.OnClickListener { dialog, which ->
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                list.devices.removeAt(position)
                                adapter.dataChanged(list.devices)
                            }
                            DialogInterface.BUTTON_NEGATIVE -> {
                            }
                        }
                    }

                val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show()


            }

        }

        ApiUtils.usersDAOInterface().getItems().enqueue(object :
            Callback<Devices> {
            override fun onResponse(
                call: Call<Devices>,
                response: Response<Devices>
            ) {
                response.body()?.let {
                    list = it
                    adapter = ListAdapter(this@MainActivity,sort(list.devices),listener)
                    recyclerview.adapter = adapter
                }

            }



            override fun onFailure(call: Call<Devices>, t: Throwable) {
               t.printStackTrace()
              Log.e("error",t.message.toString())
            }


        })

    }

    fun sort(Viewed: ArrayList<DeviceModel>): ArrayList<DeviceModel> {
        if (Viewed != null && Viewed.size > 0) {
            Collections.sort(Viewed, Comparator<DeviceModel> { first, second ->
                first.pkDevice - second.pkDevice
            })
            //AddSeperators(Viewed);
            return Viewed
        }
        return ArrayList<DeviceModel>()
    }

}