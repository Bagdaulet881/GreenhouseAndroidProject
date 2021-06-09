package com.example.greenhousev2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DevicesAdapter (private val mContacts: List<String>,
                      private val listener: OnItemClickListener
                      ) : RecyclerView.Adapter<DevicesAdapter.ViewHolder>()
{
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView),
    View.OnClickListener{
        val nameTextView = itemView.findViewById<TextView>(R.id.tvDeviceName)
        init {
            listItemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position!=RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevicesAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.recycler_view_element, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: DevicesAdapter.ViewHolder, position: Int) {
        val contact: String = mContacts.get(position)
        val textView = viewHolder.nameTextView
        textView.setText(contact)
    }

    override fun getItemCount(): Int {
        return mContacts.size
    }
}