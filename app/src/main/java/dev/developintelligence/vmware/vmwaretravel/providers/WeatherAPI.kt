package dev.developintelligence.vmware.vmwaretravel.providers

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import dev.developintelligence.vmware.vmwaretravel.datamodel.Weather
import java.net.URLEncoder

class WeatherAPI {

    fun getWeather(context: Context, callback: (Weather?) -> Unit) {
        // We need to convert spaces and special characters into a URL-valid format
        var cityName = "Palo Alto"
        cityName = URLEncoder.encode(cityName, "utf-8")

        val url =
            "https://api.openweathermap.org/data/2.5/weather?q=${cityName}&appid=0685c4e8066b577d449babf619cf4ab4&units=imperial"

        val queue = Volley.newRequestQueue(context)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val temperature = response.getJSONObject("main").getDouble("temp")
                val weather = Weather()
                weather.cityName = cityName
                weather.temperature = temperature
                callback(weather)
            },
            { error ->
                println(error)
                callback(null)
            }
        )
        queue.add(jsonObjectRequest)
    }
}