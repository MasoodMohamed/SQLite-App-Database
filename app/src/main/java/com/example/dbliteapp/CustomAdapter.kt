package com.example.dbliteapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TableRow
import android.widget.TextView

class CustomAdapter (var context: Context, var data: ArrayList<DataItem>): BaseAdapter(){

    private class ViewHolder(row: View?){
        var name:TextView
        var profession:TextView
        var residence:TextView
        var password:TextView
        var imgdelete: ImageView


     init {

         this.name= row?.findViewById(R.id.tvname) as TextView
         this.profession= row?.findViewById(R.id.tvprofession) as TextView
         this.residence= row?.findViewById(R.id.tvresidence) as TextView
         this.password= row?.findViewById(R.id.tvpassword) as TextView
         this.imgdelete= row?.findViewById(R.id.tvimgdelete) as ImageView

       }
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null){
            val layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.card_item_row,parent,false)
            viewHolder = ViewHolder(view)
            view.tag=viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val dataitem:DataItem = getItem (position) as DataItem
        viewHolder.name.text = dataitem.name
        viewHolder.profession.text = dataitem.profession
        viewHolder.residence.text = dataitem.residence
        viewHolder.password.text = dataitem.password

        viewHolder.imgdelete.setOnClickListener {
            val item_position=getItemId(position)
        }


        return view as View
    }

    override fun getItem(position: Int): Any {
        return data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.count()
    }
}
