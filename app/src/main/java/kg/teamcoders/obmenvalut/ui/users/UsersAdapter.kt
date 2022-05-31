package kg.teamcoders.obmenvalut.ui.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kg.teamcoders.obmenvalut.R
import kg.teamcoders.obmenvalut.dto.DataModel
import kg.teamcoders.obmenvalut.ui.main.MainRecyclerViewAdapter

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.MyViewHolder>()  {

    private val list : ArrayList<String> = arrayListOf()

    fun stList(_list : ArrayList<DataModel>){
        list.clear()
        _list.forEach {
            list.add(it.moneyOtvetstveniy)
        }
    }

    class MyViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        val itemUserName : TextView = itemView.findViewById(R.id.itemUserName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_name, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemUserName.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}