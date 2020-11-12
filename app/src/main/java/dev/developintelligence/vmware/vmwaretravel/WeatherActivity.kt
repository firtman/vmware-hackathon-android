package dev.developintelligence.vmware.vmwaretravel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dev.developintelligence.vmware.vmwaretravel.providers.WeatherAPI

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        title = "Weather"

        WeatherAPI().getWeather(this)
            { weather ->
                if (weather != null) {
                    findViewById<TextView>(R.id.textTemperature).setText("${weather.temperature} ⁰F")
                } else {
                    findViewById<TextView>(R.id.textTemperature).setText("Error :(")
                }

            }

    }
}