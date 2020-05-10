package com.example.dbliteapp

import android.content.Context
import android.content.DialogInterface
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        create an instance of a Database
        val db:SQLiteDatabase = openOrCreateDatabase("appdb",Context.MODE_PRIVATE,null)

        db.execSQL("CREATE TABLE IF NOT EXISTS users(name VARCHAR,profession VARCHAR,residence VARCHAR, password VARCHAR)")

//        listen to the click event of tha add data image
        imgadd.setOnClickListener{
//            grab data from form
            val name = etname.text.trim().toString()
            val profession= etprofession.text.trim().toString()
            val residence= etresidence.text.trim().toString()
            val password= etpassword.text.trim().toString()

            if (name.isEmpty() or profession.isEmpty() or residence.isEmpty() or password.isEmpty()){
//                show a message to the user
                show_message("Missing Data","Please fill in all the field")
            }else{
//                store data into the database
                db.execSQL("INSERT INTO users VALUES('"+name+"','"+profession+"','"+residence+"','"+password+"')")
                show_message("SUCCESS","Data added Successfully")

//                clear the edit text after successfully adding dat into the database
               cleartext()
            }

        }

    }

    private fun show_message(title:String,message:String){
        val alertDialog : AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setCancelable(false)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)

        alertDialog.setPositiveButton("OK",DialogInterface.OnClickListener{dialog, which -> dialog.dismiss() })
        alertDialog.create().show()
    }

    private  fun cleartext(){
        etname.setText("")
        etprofession.setText("")
        etresidence.setText("")
        etpassword.setText("")
    }


}
