package com.madwiktor.flightbargain.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madwiktor.flightbargain.R
import com.madwiktor.model.FlightWithCurrency
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_flight.*
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber
import android.view.WindowManager
import android.content.Context
import android.graphics.Point
import androidx.core.view.isVisible
import org.threeten.bp.ZoneOffset


class FlightAdapter(context: Context) : ListAdapter<FlightWithCurrency, FlightAdapter.FlightViewHolder>(itemDiff) {

    private val imageWidth by lazy {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val p = Point()
        display.getSize(p)
        if(p.x<p.y) p.x else p.y
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_flight, parent, false)
        return FlightViewHolder(v)
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        val item = getItem(position)
        Timber.d("Width $imageWidth")
        Picasso.get()
            .load(item.flight.getImageUrlForDestinationCity())
            //.placeholder() todo
            // .error() todo error placeholder
            .fit()
            .centerCrop()
            .into(holder.image)
        holder.tv_date.text = LocalDateTime.ofInstant(
            Instant.ofEpochSecond(item.flight.dTimeUTC), ZoneOffset.UTC)
            .format(DateTimeFormatter.ISO_LOCAL_DATE)
        holder.tv_destination.text = item.flight.cityTo
        holder.tv_from_airport.text = holder.itemView.context.getString(R.string.from_airport,item.flight.cityFrom)
        holder.tv_price.text = "${item.flight.price} ${item.currency}"
        val isDirect = item.flight.routeList.size==1
        holder.tv_direct.isVisible = isDirect
        holder.tv_stopovers.isVisible = !isDirect
        holder.tv_stopovers.text = holder.tv_date.context.getString(R.string.stepovers,item.flight.routeList.size-1)
        holder.tv_page.text = "${position+1}/$itemCount"
    }


    class FlightViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer

    companion object {

        // It's used to calculate callbacks to notifyItemChanged, etc.
        val itemDiff = object : DiffUtil.ItemCallback<FlightWithCurrency>() {
            override fun areItemsTheSame(oldItem: FlightWithCurrency, newItem: FlightWithCurrency): Boolean {
                return oldItem.flight.id == newItem.flight.id
            }

            override fun areContentsTheSame(oldItem: FlightWithCurrency, newItem: FlightWithCurrency): Boolean {
                return newItem == oldItem
            }

        }
    }
}