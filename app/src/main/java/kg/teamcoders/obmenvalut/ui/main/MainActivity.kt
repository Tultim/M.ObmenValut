package kg.teamcoders.obmenvalut.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kg.teamcoders.obmenvalut.R
import kg.teamcoders.obmenvalut.databinding.ActivityMainBinding
import kg.teamcoders.obmenvalut.dto.AppDataBase
import kg.teamcoders.obmenvalut.dto.DataModel
import kg.teamcoders.obmenvalut.ui.users.UserActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: MainRecyclerViewAdapter
    private lateinit var db : AppDataBase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "database"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        adapter = MainRecyclerViewAdapter(applicationContext)
    }



    override fun onResume() {
        super.onResume()
        swipeData()
        binding.swipe.setOnRefreshListener {
            swipeData()
            binding.swipe.isRefreshing = false
        }
        binding.recyclerViewMain.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMain.adapter = adapter

        binding.sdelkaButton.setOnClickListener {
            pokupkaProdazha()
        }
        binding.popolnitButton.setOnClickListener {
            popolnit()
        }
        binding.izmenitButton.setOnClickListener {
            startActivity(Intent(this,UserActivity::class.java))
        }
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    private fun swipeData(){
        val entity = db.daoInterface().getAllMoney()
        val ar : ArrayList<DataModel> = arrayListOf()
        entity.forEach {
            ar.add(it)
        }
        binding.countTextView.text = getString(R.string.count) + ar.size
        adapter.setList(ar)
        adapter.notifyDataSetChanged()
    }


    private fun pokupkaProdazha() {
        val builder = android.app.AlertDialog.Builder(this, R.style.CustomAlertDialog)
            .create()
        val view = LayoutInflater.from(this).inflate(R.layout.alert_dialog,null)
        builder.setView(view)

        val valutaName : EditText = view.findViewById(R.id.valutaNameAlertDialogEditText)
        val valutaNum : EditText = view.findViewById(R.id.valutaNumAlertDialogEditText)

        val pokupkaButton : Button = view.findViewById(R.id.pokupkaButton)
        val prodazhaButton : Button = view.findViewById(R.id.prodazhaButton)

        pokupkaButton.setOnClickListener {
            if(valutaName.text.isNotEmpty() && valutaNum.text.isNotEmpty()) try {
                val oo = db.daoInterface().getMoneyByName(valutaName.text.toString())
                val money = oo[0].moneyNum + valutaNum.text.toString().toInt()
                db.daoInterface().updateMoney(valutaName.text.toString(),money)
                Toast.makeText(applicationContext,"Успешно!",Toast.LENGTH_SHORT).show()
                swipeData()
                builder.dismiss()
            }catch (e:Exception){
                Toast.makeText(applicationContext,"Ошибка",Toast.LENGTH_SHORT).show()
            }
        }
        prodazhaButton.setOnClickListener {
            if(valutaName.text.isNotEmpty() && valutaNum.text.isNotEmpty()) try {
                val oo = db.daoInterface().getMoneyByName(valutaName.text.toString())
                if(oo[0].moneyNum - valutaNum.text.toString().toInt() < 0){
                    Toast.makeText(applicationContext,"Слишком большая сумма!",Toast.LENGTH_SHORT).show()
                }else{
                    val money = oo[0].moneyNum - valutaNum.text.toString().toInt()
                    db.daoInterface().updateMoney(valutaName.text.toString(),money)
                    Toast.makeText(applicationContext,"Успешно!",Toast.LENGTH_SHORT).show()
                    swipeData()
                    builder.dismiss()
                }
            }catch (e:Exception){
                Toast.makeText(applicationContext,"Ошибка",Toast.LENGTH_SHORT).show()
            }
        }

        builder.setCanceledOnTouchOutside(true)
        builder.show()
    }

    private fun popolnit() {
        val builder = android.app.AlertDialog.Builder(this, R.style.CustomAlertDialog)
            .create()
        val view = LayoutInflater.from(this).inflate(R.layout.alert_dialog,null)
        builder.setView(view)

        val valutaName : EditText = view.findViewById(R.id.valutaNameAlertDialogEditText)
        val valutaNum : EditText = view.findViewById(R.id.valutaNumAlertDialogEditText)

        val pokupkaButton : Button = view.findViewById(R.id.pokupkaButton)
        val prodazhaButton : Button = view.findViewById(R.id.prodazhaButton)
        prodazhaButton.visibility = View.GONE
        pokupkaButton.text = "Пополнить"
        pokupkaButton.setOnClickListener {
           try {
                val oo = db.daoInterface().getMoneyByName(valutaName.text.toString())
                val money = oo[0].moneyNum + valutaNum.text.toString().toInt()
                db.daoInterface().updateMoney(valutaName.text.toString(),money)
                Toast.makeText(applicationContext,"Успешно!",Toast.LENGTH_SHORT).show()
               swipeData()
                builder.dismiss()
            }catch (e:Exception){
                Toast.makeText(applicationContext,"Ошибка",Toast.LENGTH_SHORT).show()
            }
        }

        builder.setCanceledOnTouchOutside(true)
        builder.show()
    }
}