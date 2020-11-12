package dev.developintelligence.vmware.vmwaretravel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        title = "vmware Travel Assistant"

        findViewById<Button>(R.id.buttonMyTrips).setOnClickListener {
            val intent = Intent(this, TripsActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.buttonWeather).setOnClickListener {
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.buttonLogout).setOnClickListener {
            finish() // we go back to previous activity
        }

    }
}