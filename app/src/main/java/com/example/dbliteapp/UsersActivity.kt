package com.example.dbliteapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant","Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        adduser.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

//        Pull data from DB an display it in listview
//        Open DB
        val db: SQLiteDatabase = openOrCreateDatabase("appdb", Context.MODE_PRIVATE,null)

//Pull data from the users table
    val sql = "SELECT * FROM users"

//        create user list array
        val users : ArrayList<DataItem> = ArrayList()

        val cursor = db.rawQuery(sql,null)

        if(cursor.count == 0){
            show_message("No USERS","Seems like the DataBase is empty")
        }else{
            while (cursor.moveToNext()){
                users.add(
                    DataItem(
                        cursor.getString(0),//name
                        cursor.getString(1),//profession
                        cursor.getString(2),//residence
                        cursor.getString(3)//password
                    )
                )
            }
            userlist.adapter = CustomAdapter(this,users)
        }






    }


    private fun show_message(title:String,message:String){
        val alertDialog : AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setCancelable(false)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)

        alertDialog.setPositiveButton("OK", DialogInterface.OnClickListener{ dialog, which -> dialog.dismiss() })
        alertDialog.create().show()
    }

}
