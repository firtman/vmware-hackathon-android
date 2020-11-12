package dev.developintelligence.vmware.vmwaretravel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import dev.developintelligence.vmware.vmwaretravel.datamodel.Trip
import dev.developintelligence.vmware.vmwaretravel.providers.TripsStore

class TripsNewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trips_new)

        title = "New Trip"

        findViewById<Button>(R.id.buttonSave).setOnClickListener {
            val name = findViewById<EditText>(R.id.editNameTrip).text.toString()
            val description = findViewById<EditText>(R.id.editDescription).text.toString()
            val trip = Trip(name, description)
            TripsStore.instance.add(trip)
            finish() // go back to previous activity
        }
    }
}