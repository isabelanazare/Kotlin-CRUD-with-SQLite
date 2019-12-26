package com.example.app2

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app2.model.TVSeries
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    internal var dbHelper = DatabaseHelper(this)

    fun showToast(text: String){
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_LONG).show()
    }

    fun showDialog(title : String,Message : String){
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }

    fun clearEditTexts(){
        titleTxt.setText("")
        descriptionTxt.setText("")
        ratingTxt.setText("")
        idTxt.setText("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewData()
        handleInserts()
        handleUpdates()
        handleDeletes()
    }

    fun handleInserts() {
        insertBtn.setOnClickListener {
            try {
                dbHelper.insertData(titleTxt.text.toString(),descriptionTxt.text.toString(),
                    ratingTxt.text.toString())
                clearEditTexts()
            }catch (e: Exception){
                e.printStackTrace()
                showToast(e.message.toString())
            }
        }
    }

    fun handleUpdates() {
        updateBtn.setOnClickListener {
            try {
                val isUpdate = dbHelper.updateData(idTxt.text.toString(),
                    titleTxt.text.toString(),
                    descriptionTxt.text.toString(), ratingTxt.text.toString())
                if (isUpdate == true)
                    showToast("Data Updated Successfully")
                else
                    showToast("Data Not Updated")
            }catch (e: Exception){
                e.printStackTrace()
                showToast(e.message.toString())
            }
        }
    }

    fun handleDeletes(){
        deleteBtn.setOnClickListener {
            try {
                dbHelper.deleteData(idTxt.text.toString())
                clearEditTexts()
            }catch (e: Exception){
                e.printStackTrace()
                showToast(e.message.toString())
            }
        }
    }

    fun viewData(){

        val res = dbHelper.allData
        if (res.count == 0) {
            showDialog("Error", "No Data Found")
            return
        }
        var listTVSeries = ArrayList<TVSeries>();
        while (res.moveToNext()) {
            listTVSeries.add(
                TVSeries(res.getString(0).toInt(), res.getString(1),
                    res.getString(2),
                    res.getString(3))
            );
        }

        val arrayAdapter = ArrayAdapter<TVSeries>(this,
            android.R.layout.simple_list_item_1, listTVSeries)
        tvSeriesList.adapter = arrayAdapter
    }
}