package dev.developintelligence.vmware.vmwaretravel.providers

import android.content.SharedPreferences
import androidx.core.content.edit
import dev.developintelligence.vmware.vmwaretravel.datamodel.Trip
import org.json.JSONArray
import org.json.JSONObject

class TripsStore {
    companion object {   // static value, you can access it without an instance
        var preferences: SharedPreferences? = null
        val instance = TripsStore()
    }

    var trips: MutableList<Trip> = mutableListOf()
    var observers: MutableList<()->Unit> = mutableListOf() // observers to changes in this data store

    init {
    }

    fun registerUpdatesObserver(obs: () -> Unit) {
        observers.add(obs)
    }

    fun add(trip: Trip) {
        trips.add(trip)
        save()
    }

    fun delete(trip: Trip) {
        trips.remove(trip)
        save()
    }

    fun save() {
        val jsonArray = JSONArray()
        for (trip in trips) {
            val jsonTrip = JSONObject()
            jsonTrip.put("name", trip.name)
            jsonTrip.put("description", trip.description)
            jsonArray.put(jsonTrip)
        }
        val json = jsonArray.toString()
        preferences?.edit {
            putString("trips", json)
        }
        // Sends a message to update event observers
        observers.forEach { observer ->
            observer()
        }
    }

    fun load() {
        val string = preferences?.getString("trips", "[]") ?: "[]"
        try {
            val jsonArray = JSONArray(string)
            trips = mutableListOf() // we clear the list
            for (i in 0 until jsonArray.length()) {
                val tripJSON = jsonArray.getJSONObject(i)
                val trip = Trip(tripJSON.getString("name"),
                        tripJSON.getString("description"))
                trips.add(trip)
            }
        } catch (e: Exception) {

        }

    }



}