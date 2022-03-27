package com.example.veramobile

import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class ListAdapter(private val context: Context, private var mList: ArrayList<DeviceModel>, private val listener:IListeler) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)

        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        if(ItemsViewModel.platform =="Sercomm G450") {
            Glide.with(context).load(R.drawable.vera_plus_big).into(holder.imageView)
        }else if(ItemsViewModel.platform =="Sercomm G550"){
            Glide.with(context).load(R.drawable.vera_secure_big).into(holder.imageView)
        }else if(ItemsViewModel.platform =="MiCasaVerde VeraLite"){
            Glide.with(context).load(R.drawable.vera_edge_big).into(holder.imageView)
        }else if(ItemsViewModel.platform =="Sercomm NA930"){
            Glide.with(context).load(R.drawable.vera_edge_big).into(holder.imageView)
        }else if(ItemsViewModel.platform =="Sercomm NA900"){
            Glide.with(context).load(R.drawable.vera_edge_big).into(holder.imageView)
        }else if(ItemsViewModel.platform =="Sercomm NA301"){
            Glide.with(context).load(R.drawable.vera_edge_big).into(holder.imageView)
        }else {
            Glide.with(context).load(R.drawable.vera_edge_big).into(holder.imageView)

        }

        // sets the text to the textview from our itemHolder class
        holder.platform.setText(ItemsViewModel.platform)
        holder.pkDevice.text = ItemsViewModel.pkDevice.toString()

        holder.cardView.setOnClickListener {
            if(!holder.platform.isEnabled)
                listener.onClick(position)
        }

        holder.duzenle.setOnClickListener {
            holder.platform.setEnabled(true)
            holder.duzenle.visibility = View.GONE
            showKeyboard(holder.platform)
        }

        holder.cardView.setOnLongClickListener {
            listener.onLongClick(position)
            return@setOnLongClickListener true
        }

        holder.platform.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                ItemsViewModel.platform = holder.platform.text.toString()
                listener.buttonClick(ItemsViewModel, position)
                hideKeyboard(holder.platform)
                holder.platform.setEnabled(false)
                holder.duzenle.visibility = View.VISIBLE
                return@OnKeyListener true
            }
            false
        })

    }


    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.listImage)
        val platform: EditText = itemView.findViewById(R.id.platform)
        val pkDevice: TextView = itemView.findViewById(R.id.pkDevice)
        val cardView :CardView = itemView.findViewById(R.id.cardView1)
        val duzenle :ImageView = itemView.findViewById(R.id.duzenle)
    }

    fun dataChanged(mList: ArrayList<DeviceModel>){
        this.mList = mList
        notifyDataSetChanged()
    }

    fun showKeyboard(view:View){
        val inputMethodManager: InputMethodManager? =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        if (inputMethodManager != null) {
            inputMethodManager.toggleSoftInputFromWindow(
                view.getApplicationWindowToken(),
                InputMethodManager.SHOW_FORCED, 0
            )
        }
    }


    fun hideKeyboard(view:View){
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
    }


}