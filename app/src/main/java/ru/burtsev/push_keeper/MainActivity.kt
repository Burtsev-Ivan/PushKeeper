package ru.burtsev.push_keeper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.burtsev.push_keeper.presentation.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val intent = Intent(this, NotificationListenerService::class.java)
//        startService(intent)

        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}