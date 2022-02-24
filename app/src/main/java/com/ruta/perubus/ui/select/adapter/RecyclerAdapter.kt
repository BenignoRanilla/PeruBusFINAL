package com.ruta.perubus.ui.select.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ruta.perubus.R
import com.ruta.perubus.models.Bus
import com.ruta.perubus.ui.select.listener.IBusItemListener

class RecyclerAdapter(
    private val itinerarioList: List<Bus>,
    private val busItemClickListener: IBusItemListener
) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent,
            false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itinerarioList[position]
        holder.fechaProg.text = currentItem.fechaProg
        holder.codBus.text = currentItem.codBus
        holder.tipoServicio.text = currentItem.tipoServicio
        holder.duracion.text = currentItem.duracion
        holder.distancia.text = currentItem.distancia
        holder.map3.text = currentItem.map3

        holder.rlListItem.setOnClickListener {
            busItemClickListener.onBusItemClickListener(currentItem)
        }

    }

    override fun getItemCount() = itinerarioList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fechaProg: TextView = itemView.findViewById(R.id.fechaProg)
        val codBus: TextView = itemView.findViewById(R.id.codBus)
        val tipoServicio: TextView = itemView.findViewById(R.id.tipoServicio)
        val duracion: TextView = itemView.findViewById(R.id.duracion)
        val distancia: TextView = itemView.findViewById(R.id.distancia)
        val map3: TextView = itemView.findViewById(R.id.map3)
        val rlListItem: RelativeLayout = itemView.findViewById(R.id.rlListItem)

    }

}
