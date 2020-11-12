package dev.developintelligence.vmware.vmwaretravel

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.developintelligence.vmware.vmwaretravel.datamodel.Trip
import dev.developintelligence.vmware.vmwaretravel.providers.TripsStore


class TripsAdapter() : RecyclerView.Adapter<TripsAdapter.TripViewHolder>() {

    private var counter = 0
    private var listener: ((Int)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        counter++
        Log.d("NotesAdapter", "We've been creating $counter viewholders")
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        val viewHolder = TripViewHolder(itemView)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return TripsStore.instance.trips.size
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        holder.bind(TripsStore.instance.trips[position])
        holder.itemView.setBackgroundColor(Color.WHITE)

        holder.itemView.setOnClickListener {
            Log.d("Trip Adapter", "You clicked on $position")
            listener?.invoke(position)
        }
    }


    fun setOnItemSelectionListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    /* VIEWHOLDER */
    class TripViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(trip: Trip) {
            val textView = itemView.findViewById<TextView>(android.R.id.text1)
            textView.text = trip.name

        }
    }
}
