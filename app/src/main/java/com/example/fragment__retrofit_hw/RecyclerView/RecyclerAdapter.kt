package com.example.fragment__retrofit_hw.RecyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragment__retrofit_hw.Data.DataItem
import com.example.fragment__retrofit_hw.Data.Location
import com.example.fragment__retrofit_hw.R

class RecyclerAdapter(
    var itemList: List<DataItem> = listOf(),
    var onClick: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val LOCATION_VIEW_TYPE = 1
    }

    class ViewHolderForLocation(view: View) : RecyclerView.ViewHolder(view) {
        val tvCity = view.findViewById<TextView>(R.id.tvCity)
        val tvCountry = view.findViewById<TextView>(R.id.tvCountry)

        fun bind(location: Location, onClick: ((Int) -> Unit)?) {
            tvCity.text = location.localizedName
            tvCountry.text = location.country.localizedName
//            itemView.setOnClickListener {
//                onClick?.invoke(location.key)
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LOCATION_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.location_view_holder, parent, false)
                ViewHolderForLocation(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            LOCATION_VIEW_TYPE -> {
                val item = itemList[position] as Location
                val itemHolder = holder as ViewHolderForLocation
                itemHolder.bind(item, onClick)

                holder.itemView.setOnClickListener {
                    onClick?.invoke(item.key)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is Location -> LOCATION_VIEW_TYPE
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}