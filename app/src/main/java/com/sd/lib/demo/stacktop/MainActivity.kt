package com.sd.lib.demo.stacktop

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sd.lib.stacktop.FStackTop

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 设置默认Item
        _stackTop.setDefaultItem("0")
    }

    private val _stackTop = object : FStackTop<String>() {
        override fun updateItem(item: String?) {
            Log.i(TAG, "updateItem:$item")
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_light -> _stackTop.addItem("light")
            R.id.btn_dark -> _stackTop.addItem("dark")
            R.id.btn_remove -> {
                _stackTop.removeItem("light")
                _stackTop.removeItem("dark")
            }
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}