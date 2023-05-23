package com.example.alarmmanager

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.alarmmanager.ui.login.LoginActivity
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val intent= Intent(this,LoginActivity::class.java)
        val pendingIntent=PendingIntent.getActivities(this,100, arrayOf(intent),PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        val alarmManager=getSystemService(AlarmManager::class.java) as AlarmManager


        val _liveData= MutableLiveData (LocalDateTime.now())
        val liveData: LiveData<LocalDateTime> = _liveData

        val calendar= Calendar.getInstance()
        liveData.observe(this){
            calendar.set(Calendar.HOUR_OF_DAY,17)
            calendar.set(Calendar.MINUTE,57)
            calendar.set(Calendar.SECOND,0)
            calendar.set(Calendar.AM_PM, Calendar.PM) // Set the time to PM
        }
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,1*1000,pendingIntent)
    }
}