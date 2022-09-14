package com.jokes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jokes.R
import com.jokes.models.data.ItemsViewModel
import java.util.ArrayList

class CustomAdapter(private val mList: ArrayList<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
  init {

  }
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view 
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
  
        return ViewHolder(view)
    }
  
    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]



        // sets the text to the textview from our itemHolder class
        holder.joke.text = ItemsViewModel.joke
        holder.jokeNo.text="Joke : "+(position+1)

    }
  
    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }
  
    // Holds the views for adding it to  text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val joke: TextView = itemView.findViewById(R.id.joke)
        val jokeNo: TextView = itemView.findViewById(R.id.jokeno)
    }
}