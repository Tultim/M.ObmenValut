package kg.teamcoders.obmenvalut.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kg.teamcoders.obmenvalut.R
import kg.teamcoders.obmenvalut.dto.DataModel
import java.util.ArrayList

class MainRecyclerViewAdapter(private val context : Context) : RecyclerView.Adapter<MainRecyclerViewAdapter.MyViewHolder>() {

    private var list : ArrayList<DataModel> = arrayListOf()

    fun setList(_list : ArrayList<DataModel>){
        list.clear()
        list.addAll(_list)
    }

    class MyViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.nameValuta)
        val nalichiye : TextView = itemView.findViewById(R.id.nalichieTextView)
        val num : TextView = itemView.findViewById(R.id.numTextView)
        val pokupka : TextView = itemView.findViewById(R.id.pokupkaTextView)
        val prodazha : TextView = itemView.findViewById(R.id.prodazhaTextView)
        val otvetstveniy : TextView = itemView.findViewById(R.id.otvetstveniyTextView)
        val adres : TextView = itemView.findViewById(R.id.adresTextView)
        val gorod : TextView = itemView.findViewById(R.id.gorodTextView)
        val telefon : TextView = itemView.findViewById(R.id.telefonTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.valuta_item,parent,false)
        return MyViewHolder(view)    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = list[position].moneyValuta
        if (list[position].moneyNum == 0){
            holder.nalichiye.text = "Не имеется"
        }else{
            holder.nalichiye.text = "Имеется"
        }
        holder.num.text =  context.getString(R.string.ost) + list[position].moneyNum
        holder.pokupka.text =  context.getString(R.string.pokupka) + list[position].moneyPokupka
        holder.prodazha.text = context.getString(R.string.prodazha) + list[position].moneyProdazha
        holder.otvetstveniy.text = context.getString(R.string.otvet) + list[position].moneyOtvetstveniy
        holder.adres.text =  context.getString(R.string.adres) + list[position].moneyAdres
        holder.gorod.text =  context.getString(R.string.gorod) + list[position].moneyGorod
        holder.telefon.text =  context.getString(R.string.nomer_telefona) + list[position].moneyTelefon
    }

    override fun getItemCount(): Int {
        return list.size
    }
}