package dev.developintelligence.vmware.vmwaretravel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.developintelligence.vmware.vmwaretravel.providers.TripsStore

class TripsActivity : AppCompatActivity() {

    var adapter = TripsAdapter()
    var listTrips: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trips)

        title = "My Trips"

        // Set up the Trips Store
        TripsStore.preferences = getSharedPreferences("trips", MODE_PRIVATE)
        TripsStore.instance.load()

        // Read Trips Store changes
        TripsStore.instance.registerUpdatesObserver {
            // Reload the RecyclerView (list) through its adapter
            adapter.notifyDataSetChanged()
        }
        listTrips = findViewById(R.id.listTrips)
        listTrips?.adapter = adapter
        listTrips?.layoutManager = LinearLayoutManager(this)

        // When you click on a trip, we go to details
        adapter.setOnItemSelectionListener { index ->
            val intent = Intent(this, TripsDetailsActivity::class.java)
            intent.putExtra("tripIndex", index) // we send the selected index
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // we create a menu item with "Add"
        menu?.add("Add")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // We have only one item, we guess it's "ADD" (bad practice on a real project)
        // we open the new trip activity
        val intent = Intent(this, TripsNewActivity::class.java)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}