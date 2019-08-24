package com.example.iwatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ActivityUtils
import com.example.iwatch.base.BaseActivity
import com.example.iwatch.base.BaseApplication.Companion.tempContext
import com.example.iwatch.util.AppUtil
import kotlinx.android.synthetic.main.activity_main2.*
import org.jetbrains.anko.toast
import java.io.Serializable

class MainStarterActivity : AppCompatActivity(), Serializable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityIntent = Intent(this, MainActivity::class.java)
        startActivity(activityIntent)
        finish()
    }

}
