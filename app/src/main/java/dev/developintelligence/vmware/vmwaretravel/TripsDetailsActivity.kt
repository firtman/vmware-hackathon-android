package dev.developintelligence.vmware.vmwaretravel

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import dev.developintelligence.vmware.vmwaretravel.providers.TripsStore

class TripsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trips_details)

        // we receive the current index by argument
        // we load the data in the screen
        val index = intent.getIntExtra("tripIndex", -1)
        if (index>=0) {
            val trip = TripsStore.instance.trips[index]
            title = trip.name
            findViewById<TextView>(R.id.textNoteDescription).setText(trip.description)

            findViewById<Button>(R.id.buttonDelete).setOnClickListener {
                AlertDialog.Builder(this)
                        .setTitle("Delete a Trip")
                        .setMessage("Are you sure you want to delete this trip? You can't undone this operation")
                        .setPositiveButton("Yes, delete") { _, _ ->
                            TripsStore.instance.delete(trip)
                            finish()
                        }
                        .setNegativeButton("No, cancel") { _, _ ->

                        }
                        .show()
            }
        }

    }
}