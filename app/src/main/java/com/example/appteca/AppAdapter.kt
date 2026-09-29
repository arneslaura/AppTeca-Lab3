package com.example.appteca

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class AppAdapter(
    private val onAppClick: (App) -> Unit,
    private val onFavoritoClick: (App) -> Unit
) : ListAdapter<App, AppAdapter.AppViewHolder>(AppDiffCallback()) {

    class AppViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvNombre: TextView = v.findViewById(R.id.tvNombre)
        val tvCategoria: TextView = v.findViewById(R.id.tvCategoria)
        val tvEstrella: TextView = v.findViewById(R.id.tvEstrella)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_app, parent, false)
        return AppViewHolder(v)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val app = getItem(position)
        holder.tvNombre.text = app.nombre
        holder.tvCategoria.text = app.categoria
        holder.tvEstrella.text = if (app.esFavorita) "★" else "☆"
        holder.itemView.setOnClickListener { onAppClick(app) }
        holder.tvEstrella.setOnClickListener { onFavoritoClick(app) }
    }

    class AppDiffCallback : DiffUtil.ItemCallback<App>() {
        override fun areItemsTheSame(oldItem: App, newItem: App): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: App, newItem: App): Boolean {
            return oldItem == newItem
        }
    }
}

